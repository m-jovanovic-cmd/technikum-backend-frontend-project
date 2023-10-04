package technikumbackendfrontendproject.Backend.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import technikumbackendfrontendproject.Backend.model.User;

public class UserDTO {
    @NotBlank
    //@Length(min = 3)
    private String username;
    @NotBlank
    private String status;
    @NotBlank
    private String role;
    @NotBlank
    private String email;

    @NotBlank
    private String gender;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String location;

    @NotBlank
    private String password;

    @NotBlank
    private String postcode;

    @NotBlank
    private String street;
    @NotBlank
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public User convertToUser() {
       return new User(this.getGender(),
        this.getIsAdmin(),
        this.getUsername(),
        this.getPassword(),
        this.getFirstname(),
        this.getLastname(),
        this.getEmail(),
        this.getPostcode(),
        this.getLocation(),
        this.getStreet(),
        this.getStreetnumber(),
        this.getStatus(),
        this.getRole());
    }
}
