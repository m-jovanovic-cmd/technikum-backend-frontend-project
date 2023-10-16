package technikumbackendfrontendproject.Backend.model.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import technikumbackendfrontendproject.Backend.model.Position;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.User;

import java.util.Set;

public class CartDTO {
    @NotBlank
    private Double total;

    @NotBlank
    private Long orderstatus;

    private Long amount;
    //Long userId
    @NotBlank
    private User user;

    //Set<PositionDTO>

    private Set<PositionDTO> positionDTO;

    private  Long UserId;

/*
    @NotBlank
    private Product product;

 */

    // Getter & Setter
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
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



    public CartDTO() {
        this.total = total;
        this.orderstatus = orderstatus;
        this.amount = amount;
        this.user = user;
    }

    public CartDTO(Double total, Long orderstatus, Long amount, Set<PositionDTO> positionDTO, Long userId) {
        this.total = total;
        this.orderstatus = orderstatus;
        this.amount = amount;
        this.positionDTO = positionDTO;
        UserId = userId;
    }
}
