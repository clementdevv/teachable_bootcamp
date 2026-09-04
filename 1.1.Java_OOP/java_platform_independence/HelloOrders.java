package java_platform_independence;
// File: HelloOrders.java
//
// Annotated below: every line you would type today, plus what the
// JVM does with it after javac runs.

import java.math.BigDecimal;       // (1) standard library imports

public class HelloOrders {

    public static void main(String[] args) {
        BigDecimal price = new BigDecimal("12.99"); // (2) BigDecimal, not double
        String orderId   = "ord_synth_001";

        System.out.println("Order " + orderId + " total: $" + price);
    }
}

// $ javac HelloOrders.java
// $ java -cp . HelloOrders
// Order ord_synth_001 total: $12.99