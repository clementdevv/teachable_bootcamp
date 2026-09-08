package D2_Order_Summary;

import java.util.List;
import java.util.Scanner;

public class Main {
    
    // Hardcoded customers (10 total)
    private static final List<Customer> CUSTOMERS = List.of(
        new Customer(1, "Ada Lovelace", "ada@gmail.com"),
        new Customer(2, "Ben Franklin", "ben@gmail.com"),
        new Customer(3, "Clara Barton", "clara@gmail.com"),
        new Customer(4, "David Hume", "david@gmail.com"),
        new Customer(5, "Elena Garcia", "elena@gmail.com"),
        new Customer(6, "Felix Mendelssohn", "felix@gmail.com"),
        new Customer(7, "Grace Hopper", "grace@gmail.com"),
        new Customer(8, "Hannah Arendt", "hannah@gmail.com"),
        new Customer(9, "Isaac Newton", "isaac@gmail.com"),
        new Customer(10, "Jane Austen", "jane@gmail.com")
    );
    
    private final OrderStore store;
    private Customer currentCustomer;
    private final Scanner scanner;
    
    public Main() {
        this.store = new OrderStore();
        this.scanner = new Scanner(System.in);
        this.currentCustomer = null;
    }
    
    public void start() {
        System.out.println("Welcome to the Order Management System!");
        System.out.println();
        
        // Initial authentication
        if (!authenticate()) {
            System.out.println("Goodbye!");
            return;
        }
        
        // Enter main loop
        mainLoop();
    }
    
    private boolean authenticate() {
        System.out.println();
        System.out.println("Please enter your email to continue:");
        System.out.print("> ");
        
        String email = scanner.nextLine().trim();
        
        // Authenticate
        currentCustomer = findCustomerByEmail(email);
        
        if (currentCustomer == null) {
            System.out.println("User not found.");
            return false;
        }
        
        System.out.println();
        System.out.println("Welcome, " + currentCustomer.name() + "!");
        return true;
    }
    
    private void mainLoop() {
        while (true) {
            System.out.println();
            System.out.println(" Main Menu ");
            System.out.println("1. Specify Order");
            System.out.println("2. View total paid amount");
            System.out.println("3. Switch email account");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    specifyOrder();
                    break;
                case "2":
                    viewRevenue();
                    break;
                case "3":
                    switchAccount();
                    break;
                case "4":
                    System.out.println();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void switchAccount() {
        System.out.println();
        System.out.println(" Switch Account ");
        System.out.println("Current user: " + currentCustomer.name() + " (" + currentCustomer.email() + ")");
        System.out.println();
        
        // Show all available customers
        System.out.println("Available accounts:");
        for (Customer customer : CUSTOMERS) {
            System.out.println("  " + customer.id() + ". " + customer.name() + " (" + customer.email() + ")");
        }
        System.out.println();
        
        System.out.print("Enter email address to switch to: ");
        String email = scanner.nextLine().trim();
        
        Customer newCustomer = findCustomerByEmail(email);
        
        if (newCustomer == null) {
            System.out.println("User not found. Staying as " + currentCustomer.name() + ".");
            return;
        }
        
        if (newCustomer.equals(currentCustomer)) {
            System.out.println("You are already logged in as " + currentCustomer.name() + ".");
            return;
        }
        
        currentCustomer = newCustomer;
        System.out.println("Switched to: " + currentCustomer.name() + " (" + currentCustomer.email() + ")");
    }
    
    private void specifyOrder() {
        System.out.println();
        System.out.println(" Specify Order ");
        System.out.println("Current user: " + currentCustomer.name());
        System.out.println();
        System.out.println("Enter order status (1 for PAID, 2 for UNPAID):");
        System.out.print("> ");
        
        String statusChoice = scanner.nextLine().trim();
        
        // Validate status choice
        if (!statusChoice.equals("1") && !statusChoice.equals("2")) {
            throw new IllegalArgumentException("Invalid status choice. Must be 1 or 2.");
        }
        
        OrderStatusEnum status = statusChoice.equals("1") 
            ? OrderStatusEnum.PAID 
            : OrderStatusEnum.UNPAID;
        
        System.out.println("Enter order amount:");
        System.out.print("> ");
        String amountInput = scanner.nextLine().trim();
        
        // Validate amount
        Money amount;
        try {
            double amountValue = Double.parseDouble(amountInput);
            if (amountValue <= 0) {
                throw new IllegalArgumentException("Amount must be positive.");
            }
            amount = new Money(amountValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount. Must be a number.");
        }
        
        // Add the order
        String orderId = store.addOrder(currentCustomer, amount, status);
        
        String statusText = status == OrderStatusEnum.PAID ? "paid" : "unpaid";
        System.out.println();
        System.out.println("Order " + orderId + " (" + statusText + ", $" + amountInput + ") added successfully!");
    }
    
    private void viewRevenue() {
        System.out.println();
        System.out.println(" Revenue Report ");
        System.out.println();
        
        // Get the report from the store
        RevenueReport report = store.getPaidRevenueReport();
        
        // Check if there are any paid orders
        if (report.entries().isEmpty()) {
            System.out.println("No paid orders yet.");
            System.out.println("Total: 0.00");
            System.out.println();
            return;
        }
        
        // Display per-customer totals (already sorted)
        for (RevenueReport.Entry entry : report.entries()) {
            Customer customer = entry.customer();
            Money total = entry.total();
            System.out.printf("%s — $%.2f%n", customer.name(), total.amount().doubleValue());
        }
        
        // Display grand total at the end with a separator
        System.out.println("----------------------------");
        System.out.printf("Total — $%.2f%n", report.grandTotal().amount().doubleValue());
        System.out.println();
    }
    
    private Customer findCustomerByEmail(String email) {
        for (Customer customer : CUSTOMERS) {
            if (customer.email().equalsIgnoreCase(email)) {
                return customer;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}