package org.endless.erp.share.util.object;

import org.endless.erp.share.ddd.formula.Formula;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ObjectToMongoObjectTest {


    @Test
    void test() {
        var boolen = "true";

        var test = new Formula.Material("123", new BigDecimal("12345"));

        System.out.println(ObjectToMongoObject.convert(test));
    }

}