package technikumbackendfrontendproject.Backend.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "cart")
public class Cart {
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToMany(mappedBy = "cart")
    @JsonBackReference
    private Set<Position> positions;

    // Constructors

    public Cart() {
        this.total = 0;
    }

    public Cart(User user) {
        this.user = user;
        this.total = 0;
    }

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }
}