package D1_Shopping_cart;

public class BasketItem {
    private final Product product;
    private final int quantity; 

    public BasketItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product required"); 
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        } 
        this.product = product; 
        this.quantity = quantity;
    }

    public Product product() {
        return this.product;
    }

    public int quantity() {
        return this.quantity;
    }

    public Money lineTotal() {
        return this.product.unitPrice().multiply(this.quantity);
    }
}
