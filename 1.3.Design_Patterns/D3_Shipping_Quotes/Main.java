import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize core components
        ShippingStrategyFactory factory = new ShippingStrategyFactory();
        ShippingCalculator calculator = new ShippingCalculator(factory);

        System.out.println("Welcome to Vite-Voyage Shipping Services. Please provide the package's weight (in grams) below:");

        // 2. Read weight from CLI with input validation
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine().trim();
            double packageWeight;

            try {
                packageWeight = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Weight must be a valid number format.", e);
            }

            if (packageWeight <= 0) {
                throw new IllegalArgumentException("Package weight must be greater than 0.");
            }

            // 3. Display quote outputs dynamically across supported choices
            System.out.printf("%nBelow are the shipping quotes for your %.0f gram package:%n", packageWeight);

            int optionNumber = 1;
            for (ShippingOptionEnum option : factory.getAllStrategies().keySet()) {
                ShippingQuote quote = calculator.getQuote(option, packageWeight);
                System.out.printf("%d. %s - %s%n", 
                    optionNumber++, 
                    capitalize(option.name()), 
                    quote.cost()
                );
            }

        } catch (IllegalArgumentException e) {
            System.err.println("\nError: " + e.getMessage());
        }
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
