package cm;

import java.math.BigDecimal;

public class VisitorCost extends CostStrategy {

    @Override
    BigDecimal modify(BigDecimal rate) {
        rate = rate.subtract(BigDecimal.valueOf(10));
        if(rate.compareTo(BigDecimal.valueOf(0)) > 0) {
            rate = BigDecimal.valueOf(rate.intValue() / 2.0);
            return rate;
        }
        else {
            return BigDecimal.valueOf(0);
        }
    }
}
