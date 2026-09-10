public class ExpressShippingStrategy extends AbstractShippingStrategy {
    public ExpressShippingStrategy() {
        super(new Money(10.00), 500.0, new Money(0.02));
    }

    @Override
    public ShippingOptionEnum getOption() {
        return ShippingOptionEnum.EXPRESS;
    }
}
