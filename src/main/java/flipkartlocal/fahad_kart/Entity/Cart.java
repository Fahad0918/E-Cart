package flipkartlocal.fahad_kart.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "user_id")
    private int userId;

    // ⚠️ IMPORTANT: @ManyToOne se Product ka data Cart ke saath fetch hoga
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Products product;

    @Column(name = "product_id") // Duplicate field to hold the FK value
    private int productId;

    // Default No-Arg Constructor (JPA ke liye zaroori)
    public Cart() {
    }

    // Correct Constructor for creating a new cart item
    public Cart(int userId, int productId) {
        this.userId = userId;
        this.productId = productId; // productId ko initialize kiya gaya
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    // Naya Getter for the linked Product object
    public Products getProduct() { return product; }
    public void setProduct(Products product) { this.product = product; }
}