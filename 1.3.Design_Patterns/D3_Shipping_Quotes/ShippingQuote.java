public record ShippingQuote(ShippingOptionEnum option, double packageWeight, Money cost) {
    @Override
    public String toString() {
        return String.format("%s - %s", option, cost);
    }
}
