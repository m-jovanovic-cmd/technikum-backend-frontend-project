package technikumbackendfrontendproject.Backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entity class representing a product in the system's inventory.
 *
 * This class establishes a Many-to-One relationship with the Tax entity, indicating that many products can have one associated tax.
 *
 * @ManyToOne Relationship: Many Products to One Tax
 * @JoinColumn: Specifies the foreign key (taxId) that links a product to its associated tax.
 */
@Entity(name = "Product")

public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "taxId", nullable = false)
    private Tax tax;

    
    // CONSTRUCTORS
    
    public Product(String name, String description, String imageUrl, double price, int quantity, String type) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.status = true;
    }

    public Product(Long id, String name, String description, String imageUrl, double price, int quantity, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.status = true;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean active) {
        this.status = active;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax =tax;
    }   
}