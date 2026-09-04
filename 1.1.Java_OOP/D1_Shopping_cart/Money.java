package D1_Shopping_cart;

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

    public Money(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    public Money multiply(int quantity) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }

    public Money add(Money other) { 
        if (other == null) {
            throw new IllegalArgumentException("Cannot add null money"); 
        }
        return new Money(this.amount.add(other.amount));
    } 

    public Money subtract(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot subtract null money"); 
        } 
        BigDecimal result = this.amount.subtract(other.amount); 
        // if statement to prevent money from going below 0:
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            return Money.ZERO; 
        }
        return new Money(result);
    }

    
    
}
