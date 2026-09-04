
// This is a deliverable exercise. 
/* 
Instructions: Build an immutable Product class

The setup. 
A Product in the catalog service is the kind of value that should never 
silently change after construction — pricing, fulfilment, and inventory all key
 off the SKU and the weight. The class enforces this by exposing no setters 
 at all; if you need a different product, build a new instance.

Your task.
Implement the Product class with proper encapsulation. Required:

1. Fields: id (String), sku (String), name (String), weightGrams (int). All private final.
2. One parameterised constructor in the order above. Validate: id and sku non-blank; weightGrams >= 0. Throw IllegalArgumentException with a useful message on violation.
3. Records-style accessors: id(), sku(), name(), weightGrams().
4. No setters.
Hint.
"Records-style accessors" means a no-argument method per field whose name is the field name (e.g. String sku() { return sku; }) — that’s how Java records expose their components, and your tests rely on the same shape. Validate inputs first, then assign — if any check fails, the constructor throws and no half-built object exists.
*/
package encapsulation;

public class Product {
    // Declare private final fields here
    private final String id; 
    private final String sku; 
    private final String name; 
    private final int weightGrams; 

    public Product(String id, String sku, String name, int weightGrams) {
        // Validate then assign
        
        if ((id == null || id.isBlank()) && (sku == null || sku.isBlank())) {
            throw new IllegalArgumentException("Id must not be blank"); 
        }
        
        this.id = id; 
        this.sku = sku ;
        this.name = name;
        this.weightGrams = weightGrams; 

    }
    public String id() { return id; }
    public String sku() { return sku; }
    public String name() { return name; }
    public int weightGrams() { return weightGrams; }
}
