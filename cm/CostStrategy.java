package cm;

import java.math.BigDecimal;

public abstract class CostStrategy {

    abstract BigDecimal modify(BigDecimal standardRate);
}