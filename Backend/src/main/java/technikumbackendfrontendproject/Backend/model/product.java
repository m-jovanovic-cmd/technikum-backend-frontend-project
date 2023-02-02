package technikumbackendfrontendproject.Backend.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name = "Product")

public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "produkttyp")
    private String produkttyp;

    @Column(name = "beschreibung")
    private String beschreibung;

    //@ManyToMany(mappedBy = "products")
    //private Set<Cart> carts;

    
    // CONSTRUCTORS
    
    public Product(Long id, String name, String produkttyp, String beschreibung) {
        this.id = id;
        this.name = name;
        this.produkttyp = produkttyp;
        this.beschreibung = beschreibung;
    }

    public Product(String name, String produkttyp, String beschreibung) {
        this.name = name;
        this.produkttyp = produkttyp;
        this.beschreibung = beschreibung;
    }

    public Product() {
    }


    // GETTER & SETTER

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProdukttyp() {
        return produkttyp;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProdukttyp(String produkttyp) {
        this.produkttyp = produkttyp;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /*public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    } */
}