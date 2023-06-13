package org.endless.erp.share.util.object;

import java.math.BigDecimal;
import org.endless.erp.share.ddd.formula.Formula;
import org.junit.jupiter.api.Test;

class ObjectToMongoObjectTest {

  @Test
  void test() {
    var boolen = "true";

    var test = new Formula.Material("123", new BigDecimal("12345"));

    System.out.println(ObjectToMongoObject.convert(test));
  }
}
