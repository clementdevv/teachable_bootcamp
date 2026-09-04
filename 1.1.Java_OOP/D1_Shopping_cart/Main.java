package D1_Shopping_cart;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Basket basket = new Basket("B100");

        // Available Catalog items
        Product mug = new Product("P1", "Mug", 12.50);
        Product notebook = new Product("P2", "Notebook", 5.00);
    
        System.out.println("Welcome to Shopping Basket CLI ");

        boolean running = true;
        while (running) {
            System.out.println("SHOPPING CART ---");
            System.out.println("1. List Available Products");
            System.out.println("2. Add Mug ($12.50)");
            System.out.println("3. Add Notebook ($5.00)");
            System.out.println("4. View Basket & Subtotal");
            System.out.println("5. Apply Discount: No Discount");
            System.out.println("6. Apply Discount: 10% Off");
            System.out.println("7. Checkout (View Final Total)");
            System.out.println("8. Clear Basket");
            System.out.println("9. Exit");
            System.out.print("Choose an option (1-9): ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.println("\n=== AVAILABLE PRODUCTS ===");
                        System.out.printf("- %s: $%s%n", mug.name(), mug.unitPrice().amount());
                        System.out.printf("- %s: $%s%n", notebook.name(), notebook.unitPrice().amount());
                    }
                    case "2" -> {
                        System.out.print("Enter quantity of Mugs: ");
                        int qty = Integer.parseInt(scanner.nextLine().trim());
                        basket.addProduct(mug, qty);
                        System.out.println("Added " + qty + " Mug(s) to basket.");
                    }
                    case "3" -> {
                        System.out.print("Enter quantity of Notebooks: ");
                        int qty = Integer.parseInt(scanner.nextLine().trim());
                        basket.addProduct(notebook, qty);
                        System.out.println("Added " + qty + " Notebook(s) to basket.");
                    }
                    case "4" -> displayBasketSummary(basket, false);
                    case "5" -> {
                        basket.applyDiscountPolicy(new NoDiscountPolicy());
                        System.out.println("Applied Policy: No Discount");
                    }
                    case "6" -> {
                        basket.applyDiscountPolicy(new PercentageDiscountPolicy(0.10));
                        System.out.println("Applied Policy: 10% Off");
                    }
                    case "7" -> displayBasketSummary(basket, true);
                    case "8" -> {
                        basket.clear();
                        System.out.println("Basket has been cleared.");
                    }
                    case "9" -> {
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input Error: Quantity must be a valid whole number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Validation Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayBasketSummary(Basket basket, boolean isCheckout) {
        System.out.println("\n=== " + (isCheckout ? "CHECKOUT" : "CURRENT BASKET") + " ===");
        if (basket.items().isEmpty()) {
            System.out.println("Your basket is empty.");
        } else {
            for (BasketItem item : basket.items()) {
                System.out.printf("- %s (x%d) @ $%s each = $%s%n",
                        item.product().name(),
                        item.quantity(),
                        item.product().unitPrice().amount(),
                        item.lineTotal().amount());
            }
        }
        System.out.println("                    ");
        System.out.printf("Subtotal : $%s%n", basket.subtotal().amount());
        if (isCheckout) {
            System.out.printf("Total    : $%s%n", basket.total().amount());
        }
    }
}