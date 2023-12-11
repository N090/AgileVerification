package cm;

import java.math.BigDecimal;

class StudentCost implements CostStrategy{
    @Override
    public BigDecimal CostBehaviour(BigDecimal amount) {
        if (amount.doubleValue() > 5.5) {
            return BigDecimal.valueOf(amount.doubleValue()).subtract(
                    BigDecimal.valueOf(amount.doubleValue()).multiply(BigDecimal.valueOf(0.33)));
        }
        return amount;
    }
}