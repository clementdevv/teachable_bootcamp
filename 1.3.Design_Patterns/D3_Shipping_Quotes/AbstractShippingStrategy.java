public abstract class AbstractShippingStrategy implements ShippingStrategy{
    private final Money baseFee; 
    private final double weightThreshold; 
    private final Money excessRatePerGram; 

    protected AbstractShippingStrategy(Money baseFree, double weightThreshold, Money excessRatePerGram) {
        this.baseFee = baseFree;
        this.weightThreshold = weightThreshold; 
        this.excessRatePerGram = excessRatePerGram;         
    }

    @Override 
    public Money calculateCost(double packageWeight) {
        if (packageWeight <= 0) {
            throw new IllegalArgumentException("Package weight must be greater than 0");
        }

        Money total = baseFee;
        if (packageWeight > weightThreshold) {
            double excessWeight = packageWeight - weightThreshold;
            Money excessFee = excessRatePerGram.multiply(excessWeight);
            total = total.add(excessFee);
        }
        return total;
    }    
}
