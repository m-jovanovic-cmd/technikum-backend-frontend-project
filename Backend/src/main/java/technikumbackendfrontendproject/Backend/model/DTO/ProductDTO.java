package technikumbackendfrontendproject.Backend.model.DTO;

public class ProductDTO {
    
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private int quantity;
    private String type;
    private boolean status;
    private Long taxId;


    // Getter & Setter

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
    public Long getTaxId() {
        return taxId;
    }
    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }
}
