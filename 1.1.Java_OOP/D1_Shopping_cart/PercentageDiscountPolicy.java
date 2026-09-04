package D1_Shopping_cart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscountPolicy implements DiscountPolicy {
    private final BigDecimal percentage;

    public PercentageDiscountPolicy(double percentageAsDecimal) {
        if (percentageAsDecimal < 0.0 || percentageAsDecimal > 1.0) {
            throw new IllegalArgumentException("Discount percentage must be between 0.0 and 1.0");
        }
        this.percentage = BigDecimal.valueOf(percentageAsDecimal);
    }

    @Override
    public Money applyDiscount(Money subtotal) {
        if (subtotal == null) {
            throw new IllegalArgumentException("Subtotal cannot be null");
        }
        BigDecimal discount = subtotal.amount()
                .multiply(this.percentage)
                .setScale(2, RoundingMode.HALF_UP);
                
        return subtotal.subtract(new Money(discount));
    }
}
