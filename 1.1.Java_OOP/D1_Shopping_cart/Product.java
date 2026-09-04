package D1_Shopping_cart;

public class Product {
    private final String id; 
    private final String name; 
    private final Money unitPrice; 

    public Product(String id, String name, Money unitPrice) {
        if (id == null || id.isBlank() || name == null || name.isBlank() || unitPrice == null) {
            throw new IllegalArgumentException("Invalid Product details");
        }
        this.id = id; 
        this.name = name; 
        this.unitPrice = unitPrice;
    }

    // Constructor overloading: (had to do this in order for the pre-configured product catalog in Main.dart to work)
    public Product(String id, String name, double unitPrice) {
        this(id, name, new Money(unitPrice));
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    } 

    public Money unitPrice() {
        return unitPrice;
    }
}
