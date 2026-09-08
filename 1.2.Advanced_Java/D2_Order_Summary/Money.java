package D2_Order_Summary;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money {
        if (amount == null) {
            throw new IllegalArgumentException("Money amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money amount cannot be negative");
        }

    }

    public Money(double amount)  {
        this(BigDecimal.valueOf(amount));
    } 

    public Money add(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot add null money"); 
        }
        return new Money(this.amount.add(other.amount));
    }

    public static Money sum(Money a, Money b) {
        if (a == null) return b;
        if (b == null) return a;
        return a.add(b);
    }
    
    @Override
    public String toString() {
        return String.format("%.2f", amount.doubleValue());
    }
     
    // I don't think we need subtract. We also would need multiply only if we had products being tallied. 
}
