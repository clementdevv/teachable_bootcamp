import java.util.Map;
import java.util.List;
import java.util.HashMap;

record Order(String id, String customerId, double amount) {}

public class OrderIndex {
    static Map<String, Order> byId(List<Order> orders) {
        Map<String, Order> index = new HashMap<>();
        for (Order order : orders) {
            index.put(order.id(), order);
        }
        return index;
    }
}
