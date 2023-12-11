package cm;

import java.math.BigDecimal;

class ManagementCost implements CostStrategy{
    @Override
    public BigDecimal CostBehaviour(BigDecimal amount) {
        if (amount.doubleValue() < 5){
            return BigDecimal.valueOf(5);
        }
        return amount;
    }
}