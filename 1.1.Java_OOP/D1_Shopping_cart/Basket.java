package D1_Shopping_cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final String id; 
    private DiscountPolicy discountPolicy; // Not final because policy might be swapped
    private final List<BasketItem> basketItems; 

    public Basket(String id) {
        if(id == null || id.isBlank()) {
            throw new IllegalArgumentException("Basket Id cannot be empty");
        }

        this.id = id; 
        this.basketItems = new ArrayList<>(); 
        this.discountPolicy = new NoDiscountPolicy();          
    }
    
    public String id() {
        return id; 
    }  

    public void applyDiscountPolicy(DiscountPolicy discountPolicy) {
        if (discountPolicy == null) {
            this.discountPolicy = new NoDiscountPolicy();  
        } else {
            this.discountPolicy = discountPolicy; 
        }
    } 

    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");             
        }

        // Check if product already exists in basket
        for (int i = 0; i < basketItems.size(); i++) {
            BasketItem item = basketItems.get(i); 
            if (item.product().id().equals(product.id())) {
                int updatedQuantity = item.quantity() + quantity; 
                basketItems.set(i, new BasketItem(product, updatedQuantity)); 
                return;
            }
        }
        basketItems.add(new BasketItem(product, quantity));     
    }

    public List<BasketItem> items() {
        return List.copyOf(basketItems); 
    }

    public Money subtotal() {
        if (basketItems.isEmpty()) {
            return Money.ZERO;
        }

        Money subtotal = Money.ZERO; 
        for (BasketItem item : basketItems) {
            subtotal = subtotal.add(item.lineTotal()); 
        }
        return subtotal;
    }

    public Money total() {
        Money sub = subtotal();
        if (sub.amount().compareTo(BigDecimal.ZERO) == 0) {
            return Money.ZERO;
        } 

        return discountPolicy.applyDiscount(sub);
    }

    public void clear() {
    this.basketItems.clear();
}


}
