/*package technikumbackendfrontendproject.Backend.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

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

    
    //versuch fremdschlüssel UserID zu implementieren
    @ManyToOne
    @JoinColumn(name = "ID")
    private Set<User> user;


    // GETTER & SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }
}
*/