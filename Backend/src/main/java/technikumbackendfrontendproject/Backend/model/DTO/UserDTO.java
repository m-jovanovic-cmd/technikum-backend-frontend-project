package technikumbackendfrontendproject.Backend.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdk.jfr.BooleanFlag;
import org.hibernate.validator.constraints.Length;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.model.User;

public class UserDTO {
    @NotBlank
    @Length(min = 3)
    private String username;
    @NotNull
    private String status;
    @NotNull
    private String role;
    @NotNull
    private String email;

    @NotNull
    private String gender;

    @Length(min = 3)
    private String firstname;

    @Length(min = 3)
    private String lastname;

    @Length(min = 3, max = 20)
    private String location;

    @Length(min = 3)
    private String password;

    @Length(min = 4)
    private String postcode;

    @Length(min = 4)
    private String street;
    @NotNull
    @Positive
    private String streetNumber;
    @NotNull
    private Boolean isAdmin;

    // Getter & Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstname() {return firstname;}


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetnumber() {
        return streetNumber;
    }

    public void setStreetnumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }


    public @NotNull Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = Boolean.valueOf(isAdmin);
    }

    public User convertToUser() {
       return new User(this.gender,
        this.username,
        this.password,
        this.firstname,
        this.lastname ,
        this.email ,
        this.postcode,
        this.location,
        this.street,
        this.streetNumber,
        this.status,
        this.role);
    }
}
