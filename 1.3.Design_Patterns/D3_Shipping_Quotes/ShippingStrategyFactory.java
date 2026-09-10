import java.util.EnumMap;
import java.util.Map;

public class ShippingStrategyFactory {
    private final Map<ShippingOptionEnum, ShippingStrategy> strategies = new EnumMap<>(ShippingOptionEnum.class);

    public ShippingStrategyFactory() {
        // Factory manages registration internally
        registerStrategy(new StandardShippingStrategy());
        registerStrategy(new ExpressShippingStrategy());
    }

    private void registerStrategy(ShippingStrategy strategy) {
        strategies.put(strategy.getOption(), strategy);
    }

    public ShippingStrategy getStrategy(ShippingOptionEnum option) {
        if (option == null) {
            throw new IllegalArgumentException("Shipping option choice cannot be null");
        }
        ShippingStrategy strategy = strategies.get(option);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported shipping choice: " + option);
        }
        return strategy;
    }

    public Map<ShippingOptionEnum, ShippingStrategy> getAllStrategies() {
        return Map.copyOf(strategies);
    }
}
