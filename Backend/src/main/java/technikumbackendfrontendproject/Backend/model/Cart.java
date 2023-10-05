package technikumbackendfrontendproject.Backend.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity(name = "cart")
public class Cart {
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "total")
    private Long total;

    @Column(name = "orderstatus")
    private Long orderstatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToMany(mappedBy = "cart")
    @JsonBackReference
    private Set<Position> positions;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    // Constructors

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }


    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Long orderstatus) {
        this.orderstatus = orderstatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }  
}