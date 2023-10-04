package technikumbackendfrontendproject.Backend.model.DTO;

import jakarta.validation.constraints.NotNull;
import technikumbackendfrontendproject.Backend.model.User;

public class AdminDTO {
    @NotNull
    private Boolean isAdmin;

    // Constructors, getters, and setters

    // Default constructor
    public AdminDTO() {
    }

    // Parameterized constructor
    public AdminDTO(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    // Method to convert AdminDTO to User
    public User convertToUser(User user) {
       this.getIsAdmin();
       return user;
    }
}

