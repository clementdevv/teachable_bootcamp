package D2_Order_Summary;

import java.util.Comparator;

public record Order(String id, Customer customer, Money amount, OrderStatusEnum status) {    

    public Order {
        if(id == null || id.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }

    // Named comparators for sorting
    public static final Comparator<Order> BY_AMOUNT_DESC = 
        Comparator.comparing((Order order) -> order.amount().amount())
                  .reversed()
                  .thenComparing(Order::customer, Comparator.comparing(Customer::id));
}
