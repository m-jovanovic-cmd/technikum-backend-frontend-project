package technikumbackendfrontendproject.Backend.model.DTO;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class CartProductDTO {
    private Long id;

    @NotBlank
    @Length(min = 4, max = 100)
    private String name;

    @DecimalMin("0.01")
    private double price;

    @Min(1)
    private int amount;

    public CartProductDTO(Long id, String name, double price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
