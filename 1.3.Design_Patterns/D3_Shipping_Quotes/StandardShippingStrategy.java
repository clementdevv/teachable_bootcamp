public class StandardShippingStrategy extends AbstractShippingStrategy{
    public StandardShippingStrategy() {
        super(new Money(5.00), 1000.0, new Money(0.01));
    }

    @Override
    public ShippingOptionEnum getOption() {
        return ShippingOptionEnum.STANDARD;
    }
}
