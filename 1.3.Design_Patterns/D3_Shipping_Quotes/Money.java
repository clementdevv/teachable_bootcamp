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
        return new Money(this.amount.add(other.amount));
    }

    public Money multiply(double multiplier) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)));
    }

    @Override
    public String toString() {
        return String.format("$%.2f", amount);
    }

}
