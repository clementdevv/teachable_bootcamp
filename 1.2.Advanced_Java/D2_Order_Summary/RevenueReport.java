package D2_Order_Summary;

import java.util.List;

public record RevenueReport(List<Entry> entries, Money grandTotal) {
    public record Entry(Customer customer, Money total) {
        public Entry {
            if (customer == null) {
                throw new IllegalArgumentException("Customer cannot be null");
            }
            if (total == null) {
                throw new IllegalArgumentException("Total cannot be null");
            }
        }   
    }
}
