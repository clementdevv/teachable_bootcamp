public interface ShippingStrategy {
    ShippingOptionEnum getOption(); 
    Money calculateCost(double packageWeight);
}
