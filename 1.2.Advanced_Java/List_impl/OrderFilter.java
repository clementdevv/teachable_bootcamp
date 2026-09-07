import java.util.*;

enum OrderStatus {
    PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

record Order(String id, String customerId, double amount) {}

public class OrderFilter {
    static List<Order> byStatus(List<Order> orders, OrderStatus status) {
        List<Order> result = new ArrayList<>(); 
        for (Order order : orders) {
            result.add(order); 
        }
        return result;
    }    
}
