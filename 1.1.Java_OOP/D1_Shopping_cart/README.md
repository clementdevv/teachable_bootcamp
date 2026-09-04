# Shopping Cart — Domain Model & CLI

## `Basket` Class

- **id** (`String`) – Validates against `null` or blank, throws `IllegalArgumentException`
- **discountPolicy** – Mutable field, defaults to `NoDiscountPolicy`, can be swapped via `applyDiscountPolicy()` (null values reset to `NoDiscountPolicy`)
- **basketItems** (`List`) – Internal mutable list, but exposed as immutable copy via `items()` to prevent external modification
- **addProduct()** – If product exists, updates quantity; otherwise adds new `BasketItem`
- **subtotal()** – Sums all line totals, returns `Money.ZERO` if empty
- **total()** – Applies discount policy to subtotal (returns `Money.ZERO` if subtotal is zero)

## `Money` Class

- **amount** (`BigDecimal`) – Validates against `null` and negative values
- **Record** – Uses Java Record for immutable data carrier with built-in accessors
- **ZERO** – Static constant for zero money
- **Constructors** – Overloaded for `BigDecimal` and `double` convenience
- **multiply()** – Multiplies by quantity for line totals
- **add()** – Adds two `Money` objects, validates against `null`
- **subtract()** – Subtracts `Money`, returns `Money.ZERO` if result would be negative (prevents negative money)

## `DiscountPolicy` Interface & Implementations

- **NoDiscountPolicy** – Returns subtotal unchanged, validates subtotal not null
- **PercentageDiscountPolicy** – Applies percentage discount (0.0–1.0), validates percentage range, rounds to 2 decimal places

## `BasketItem` Class

- **product** (`Product`) – Validates against `null`, throws `IllegalArgumentException` if missing
- **quantity** (`int`) – Validates must be greater than zero, throws `IllegalArgumentException` if invalid
- **Record-style accessors** – Uses `product()` and `quantity()` instead of getters
- **lineTotal()** – Calculates total by multiplying unit price by quantity

## `Product` Class

- **id, name** (`String`) – Constructor validates against `null` or blank, throws `IllegalArgumentException` if invalid
- **unitPrice** (`Money`) – Uses `Money` type for precise monetary values
- **Constructor overloading** – Provides both `Money` and `double` constructors for convenience when creating products
- **Record-style accessors** – Uses `id()`, `name()`, `unitPrice()` instead of traditional `getId()` getters for cleaner, more modern Java syntax

## Running the Application (CLI)

By running the Java file, the user should be able to:

- **List products** – View available products with IDs, names, and prices
- **Add to cart** – Add products by ID with specified quantity
- **View basket** – See current items, quantities, and subtotal
- **Apply discount** – Switch between discount policies (no discount or percentage)
- **Checkout** – View final total with applied discount
- **Clear basket** – Remove all items from the basket
- **Exit** – Quit the application

## How to Use

1. Compile: `javac D1_Shopping_cart/Main.java`
2. Run: `java D1_Shopping_cart.Main`
3. Follow the numbered menu prompts
4. Enter product IDs exactly as shown in the product catalog
5. Enter quantities as positive integers
6. For percentage discount, enter as decimal (e.g., `0.1` for 10%)