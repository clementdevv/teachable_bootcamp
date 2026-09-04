package encapsulation;


// We're demonstrating encapsulation i 3 moves:
// 1. private + final for every field
// 2. constructor validates and assigns 
// 3. public accessors return the value; no setters

public class Customer {
    private final String id; 
    private final String name; 
    private final String email; 
    private final java.time.LocalDate signuDate; 

    // 1. private + final 

    public Customer(String id, String name, String email, java.time.LocalDate signUpDate) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must be non-blank"); // 2. validate 
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        } 

        this.id = id; 
        this.email = email; 
        this.name = name; 
        this.signuDate = signUpDate; 
    }

    public String id() { return id; }
    public String name() { return name; } 
    public String email() { return email; }
    public java.time.LocalDate signUpDate() { return signuDate; }
}


// Same data using a record — every line above replaced by:
//
//   public record CustomerRecord(String id, String name, String email,
//                                java.time.LocalDate signupDate) {
//       public CustomerRecord {
//           if (id == null || id.isBlank())
//               throw new IllegalArgumentException("id must be non-blank");
//           if (email == null || !email.contains("@"))
//               throw new IllegalArgumentException("email must contain @");
//       }
//   }
