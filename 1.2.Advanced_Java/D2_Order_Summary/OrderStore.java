package D2_Order_Summary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OrderStore {
    private final List<Order> orders; 
    private final AtomicInteger nextOrderId; 

    // I am trying best to my knowledge, to ensure thread safety by concurrency. 
    // AtomicInteger type 


    public OrderStore() {
        this.orders = new ArrayList<>(); 
        this.nextOrderId = new AtomicInteger(100); 
    }

    public OrderStore(int startId) {
        this.orders = new ArrayList<>(); 
        this.nextOrderId = new AtomicInteger(startId);
    }

    public String addOrder(Customer customer, Money amount, OrderStatusEnum status) {
        Objects.requireNonNull(customer, "Customer cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
        
        String orderId = "O-" + nextOrderId;
        

        Order newOrder = new Order(orderId, customer, amount, status); 
        orders.add(newOrder);
        return orderId;
    }

    public RevenueReport getPaidRevenueReport() {
        // Step 1: Filter - keep only PAID orders
        List<Order> paidOrders = orders.stream()
            .filter(order -> order.status() == OrderStatusEnum.PAID)
            .toList(); 

        // Step 2: If empty, return empty report
        if (paidOrders.isEmpty()) {
            return new RevenueReport(List.of(), Money.ZERO); 
        }

        // Step 3: Group by customer and sum amounts using Collectors
         Map<Customer, Money> customerTotals = paidOrders.stream()
            .collect(Collectors.groupingBy(
                Order::customer,  // Method reference
                Collectors.reducing(
                    Money.ZERO,
                    Order::amount,
                    Money::sum  // Method reference to sum
                )
            ));

        // Step 4: Sort customers by total (highest first), tie-break by ID
        List<RevenueReport.Entry> sortedEntries = customerTotals.entrySet().stream()
            .sorted((entry1, entry2) -> {
                // Compare amounts (descending)
                int amountComp = entry2.getValue().amount()
                    .compareTo(entry1.getValue().amount());
                if (amountComp != 0) {
                    return amountComp;
                }
                // Tie-break by customer ID (ascending)
                return Integer.compare(entry1.getKey().id(), entry2.getKey().id());
            })
            .map(entry -> new RevenueReport.Entry(
                entry.getKey(), 
                entry.getValue()
            ))
            .toList(); 

        

        // STEP 5: Calculate grand total using stream sum
        Money grandTotal = customerTotals.values().stream()
            .reduce(Money.ZERO, Money::sum);  // Method reference
        
        return new RevenueReport(sortedEntries, grandTotal);
    }

    public int getOrderCount() {
        return (int) orders.stream().count();
    } 

    public int getPaidOrderCount() {
        return (int) orders.stream()
            .filter(order -> order.status() == OrderStatusEnum.PAID)
            .count();
    }

    // Optional: Find customer's total using Optional
    public Optional<Money> getCustomerTotal(Customer customer) {
        Objects.requireNonNull(customer, "Customer cannot be null");
        
        return orders.stream()
            .filter(order -> order.status() == OrderStatusEnum.PAID)
            .filter(order -> order.customer().equals(customer))
            .map(Order::amount)
            .reduce(Money::sum);
    }
    

    
    
}