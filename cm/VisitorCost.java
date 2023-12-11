package cm;

import java.math.BigDecimal;

class VisitorCost implements CostStrategy {

    @Override
    public BigDecimal CostBehaviour(BigDecimal amount) {
        if (amount.doubleValue() <= 10) {
            return BigDecimal.valueOf(0);
        }
        else {
            return amount.subtract(BigDecimal.valueOf(10)).multiply(BigDecimal.valueOf(0.5));
        }

    }
}

