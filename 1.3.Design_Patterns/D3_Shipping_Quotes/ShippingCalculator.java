import java.util.ArrayList;
import java.util.List;

public class ShippingCalculator {
    private final ShippingStrategyFactory factory;

    // Constructor Injection for the strategy
    public ShippingCalculator(ShippingStrategyFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Shipping strategy cannot be null");
        }
        this.factory = factory;
    }

    public ShippingQuote getQuote(ShippingOptionEnum option, double packageWeight) {
        ShippingStrategy strategy = factory.getStrategy(option);
        Money cost = strategy.calculateCost(packageWeight);
        return new ShippingQuote(option, packageWeight, cost);
    }

    public List<ShippingQuote> getAllQuotes(double packageWeight) {
        List<ShippingQuote> quotes = new ArrayList<>();
        for (ShippingOptionEnum option : factory.getAllStrategies().keySet()) {
            quotes.add(getQuote(option, packageWeight));
        }
        return quotes;
    }
}
