package cm;

import java.math.BigDecimal;

class StaffCost implements CostStrategy{
    @Override
    public BigDecimal CostBehaviour(BigDecimal amount) {
        if (amount.doubleValue() > 10.0){
            return BigDecimal.valueOf(10);
        }
        return amount;
    }
}