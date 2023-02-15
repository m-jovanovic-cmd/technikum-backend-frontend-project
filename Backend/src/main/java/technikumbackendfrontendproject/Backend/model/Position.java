package technikumbackendfrontendproject.Backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "position")
public class Position {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonManagedReference
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity)", nullable = false)
    private Integer quantity;


    // Constructors

    public Position() {
    }

    public Position(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
    
    // Getter & Setter
    
    public Long getId() {
        return id;
    }
    
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}