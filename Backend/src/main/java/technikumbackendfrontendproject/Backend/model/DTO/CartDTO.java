package technikumbackendfrontendproject.Backend.model.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import technikumbackendfrontendproject.Backend.model.Position;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.User;

import java.util.Set;

public class CartDTO {
    @NotBlank
    private Long total;

    @NotBlank
    private Long orderstatus;


    @NotBlank
    private Long amount;

    @NotBlank
    private User user;

    @NotBlank
    private Set<Position> positions;

    @NotBlank
    private Product product;

    // Getter & Setter
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
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartDTO() {
        this.total = total;
        this.orderstatus = orderstatus;
        this.amount = amount;
        this.user = user;
        this.positions = positions;
        this.product = product;
    }
}
