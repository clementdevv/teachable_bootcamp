package D1_Shopping_cart;

public class NoDiscountPolicy implements DiscountPolicy {
    @Override 
    public Money applyDiscount(Money subtotal) {
        if (subtotal == null) {
            throw new IllegalArgumentException("Subtotal cannot be null");
        }
        return subtotal;
    }
} 