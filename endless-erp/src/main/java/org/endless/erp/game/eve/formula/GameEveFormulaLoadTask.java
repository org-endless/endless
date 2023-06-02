package org.endless.erp.game.eve.formula;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.ddd.formula.Formula;
import org.endless.erp.share.mongo.bulk.BulkMongoRepository;
import org.endless.erp.share.util.date.DateFormatter;
import org.endless.erp.share.util.decimal.Decimal;
import org.endless.erp.share.util.object.ObjectToMongoObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GameEveFormulaLoadTask
 *
 * @author Deng Haozhi
 * @date 2023/5/23 8:34
 * @since 0.0.3
 */
@Log4j2
@Component("gameEveFormulaLoadTask")
public class GameEveFormulaLoadTask implements GameEveAsyncTask {

    private final BulkMongoRepository bulkRepository;

    public GameEveFormulaLoadTask(BulkMongoRepository bulkRepository) {
        this.bulkRepository = bulkRepository;
    }

    @Override
    public <T> void run(T scannerMaps) {

        long begin = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " loading begin: " + begin);

        List<Pair<Query, Update>> pairs = new ArrayList<>();

        ((List<?>) scannerMaps).forEach(scannerMap -> {

            var scanner = String.valueOf(((Map<?, ?>) scannerMap).get("scanner"));
            var formulaItemId = String.valueOf(((Map<?, ?>) scannerMap).get("formulaItemId"));
            var rat = (Map<?, ?>) ObjectToMongoObject.convert(new Yaml().load(scanner));
            var maxProductionLimit = Decimal.format(rat.get("maxProductionLimit"));
            var activities = (Map<?, ?>) rat.get("activities");

            var manufacturing = (Map<?, ?>) activities.get("manufacturing");
            if (manufacturing != null) {

                log.trace("Get manufacturing pairs");
                pairs.addAll(getPairs(manufacturing, GameEveFormula.Categories.manufacturing, formulaItemId, maxProductionLimit));
            }

            var invention = (Map<?, ?>) activities.get("invention");
            if (invention != null) {

                log.trace("Get invention pairs");
                pairs.addAll(getPairs(invention, GameEveFormula.Categories.invention, formulaItemId, maxProductionLimit));
            }

            var copying = (Map<?, ?>) activities.get("copying");
            if (copying != null) {

                log.trace("Get copying pairs");
                pairs.addAll(getPairs(copying, GameEveFormula.Categories.copying, formulaItemId, maxProductionLimit));
            }

            var researchMaterial = (Map<?, ?>) activities.get("research_material");
            if (researchMaterial != null) {

                log.trace("Get researchMaterial pairs");
                pairs.addAll(getPairs(researchMaterial, GameEveFormula.Categories.researchMaterial, formulaItemId, maxProductionLimit));
            }
            var researchTime = (Map<?, ?>) activities.get("research_time");
            if (researchTime != null) {

                log.trace("Get researchTime pairs");
                pairs.addAll(getPairs(researchTime, GameEveFormula.Categories.researchTime, formulaItemId, maxProductionLimit));
            }
        });
        log.trace(pairs);
        bulkRepository.upsert(pairs, GameEveFormula.class);

        long end = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " loading end: " + end);
        log.info("Thread: " + Thread.currentThread().getName() + " loaded completed!");
    }

    @Override
    public <T, E extends Enum<E>> void run(T scannerMaps, Enum<E> categories) {

        long begin = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " loading begin: " + begin);

        if (!categories.equals(GameEveFormula.Categories.planet)) {
            return;
        }

        List<Pair<Query, Update>> pairs = new ArrayList<>();
        var industryId = "game.eve";

        ((List<?>) scannerMaps).forEach(scanner -> {

            var rat = (Map<?, ?>) ObjectToMongoObject.convert(new Yaml().load(String.valueOf(scanner)));
            var items = (Map<?, ?>) rat.get("types");

            log.trace(items);

            List<Formula.Material> materials = new ArrayList<>();
            String itemId = "", productionPerCycle = "";

            for (var key : items.keySet()) {

                log.trace(key);

                var material = (Map<?, ?>) items.get(key);

                if (material.get("isInput").equals(true)) {
                    materials.add(new Formula.Material(String.valueOf(key), Decimal.format(material.get("quantity"))));
                }
                if (material.get("isInput").equals(false)) {
                    itemId = String.valueOf(key);
                    productionPerCycle = String.valueOf(material.get("quantity"));
                }
            }

            var id = industryId + "_" + categories + "_" + itemId;
            var query = Query.query(Criteria.where("id").is(id));
            var update = Update
                    .update("itemId", itemId)
                    .set("industryId", industryId)
                    .set("formulaItemId", itemId)
                    .set("productionPerCycle", Decimal.format((productionPerCycle)))
                    .set("probability", Decimal.format("1"))
                    .set("timePerCycle.secondsPerCycle", Decimal.format(rat.get("cycleTime")))
                    .set("maxProductionLimit.maxProductionLimitPerLine", "infinity")
                    .set("materials", materials)
                    .set("categories", categories)
                    .set("updateDateTime", DateFormatter.nowIso())
                    .set("updateTimeStamp", System.currentTimeMillis());

            pairs.add(Pair.of(query, update));
        });
        log.trace(pairs);
        bulkRepository.upsert(pairs, GameEveFormula.class);

        long end = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " loading end: " + end);
        log.info("Thread: " + Thread.currentThread().getName() + " loaded completed!");
    }

    protected List<Pair<Query, Update>> getPairs(Map<?, ?> formula, GameEveFormula.Categories categories, String formulaItemId, BigDecimal maxProductionLimit) {

        List<Pair<Query, Update>> pairs = new ArrayList<>();
        var industryId = "game.eve";

        var products = formula.get("products");
        if (products == null) {

            var materials = getMaterials(formula);
            var skills = getSkills(formula);

            var id = industryId + "_" + categories + "_" + formulaItemId;
            var query = Query.query(Criteria.where("id").is(id));
            var update = Update
                    .update("itemId", formulaItemId)
                    .set("industryId", industryId)
                    .set("formulaItemId", formulaItemId)
                    .set("productionPerCycle", Decimal.format("1"))
                    .set("probability", Decimal.format("1"))
                    .set("timePerCycle.secondsPerCycle", Decimal.format(formula.get("time")))
                    .set("maxProductionLimit.maxProductionLimitPerLine", maxProductionLimit)
                    .set("materials", materials)
                    .set("skills", skills)
                    .set("categories", categories)
                    .set("updateDateTime", DateFormatter.nowIso())
                    .set("updateTimeStamp", System.currentTimeMillis());
            pairs.add(Pair.of(query, update));
            log.trace(categories.getEnInstruction() + ": " + pairs);
            return pairs;
        }

        ((List<?>) formula.get("products")).forEach(product -> {

            var materials = getMaterials(formula);
            var skills = getSkills(formula);

            var itemId = String.valueOf(((Map<?, ?>) product).get("itemId"));
            var id = industryId + "_" + categories + "_" + itemId;
            var probability = Decimal.format(((Map<?, ?>) product).get("probability"));

            var query = Query.query(Criteria.where("id").is(id));
            var update = Update
                    .update("itemId", itemId)
                    .set("industryId", industryId)
                    .set("formulaItemId", formulaItemId)
                    .set("productionPerCycle", Decimal.format(((Map<?, ?>) product).get("quantity")))
                    .set("probability", probability == null ? Decimal.format("1") : probability)
                    .set("timePerCycle.secondsPerCycle", Decimal.format(formula.get("time")))
                    .set("maxProductionLimit.maxProductionLimitPerLine", maxProductionLimit)
                    .set("materials", materials)
                    .set("skills", skills)
                    .set("categories", categories)
                    .set("updateDateTime", DateFormatter.nowIso())
                    .set("updateTimeStamp", System.currentTimeMillis());
            pairs.add(Pair.of(query, update));
        });
        return pairs;
    }

    protected List<Formula.Material> getMaterials(Map<?, ?> formula) {

        log.debug("Get materials from formula");

        List<Formula.Material> materials = new ArrayList<>();
        if (formula.get("materials") != null) {
            ((List<?>) formula.get("materials")).stream().map(m -> ((Map<?, ?>) m)).forEach(map -> {
                String itemId = String.valueOf(map.get("itemId"));
                BigDecimal quantity = Decimal.format(map.get("quantity"));
                materials.add(new Formula.Material(itemId, quantity));
            });
        }
        return materials;
    }

    protected List<Formula.Skill> getSkills(Map<?, ?> formula) {

        log.debug("Get skills from formula");

        List<Formula.Skill> skills = new ArrayList<>();
        if (formula.get("skills") != null) {
            ((List<?>) formula.get("skills")).stream().map(s -> ((Map<?, ?>) s)).forEach(map -> {
                String itemId = String.valueOf(map.get("itemId"));
                String level = String.valueOf(map.get("level"));
                skills.add(new Formula.Skill(itemId, level));
            });
        }
        return skills;
    }

}