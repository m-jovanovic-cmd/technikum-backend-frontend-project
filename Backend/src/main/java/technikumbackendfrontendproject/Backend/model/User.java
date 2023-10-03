package technikumbackendfrontendproject.Backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotBlank
    @Column(name = "username", nullable = false)
    private String username;

    //TO DO should be hashed
    //SHA1 oder SHA256 und salting
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "admin", nullable = false)
    private Boolean admin;

    @NotBlank
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotBlank
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "postcode", nullable = false)
    private String postcode;

    @NotBlank
    @Column(name = "location", nullable = false)
    private String location;

    @NotBlank
    @Column(name = "street", nullable = false)
    private String street;

    @NotBlank
    @Column(name = "streetnumber", nullable = false)
    private String streetnumber;

    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "role", nullable = false)
    private String role;


    // CONSTRUCTORS

    public User(Long id, Boolean admin, String gender, String username, String password, String firstname, String lastname,
            String email, String postcode, String location, String street, String streetnumber, String status,
            String role) {
        this.id = id;
        this.gender = gender;
        this.admin = admin;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.postcode = postcode;
        this.location = location;
        this.street = street;
        this.streetnumber = streetnumber;
        this.status = status;
        this.role = role;
    }

    public User(String gender, Boolean admin, String username, String password, String firstname, String lastname, String email,
            String postcode, String location, String street, String streetnumber, String status, String role) {
        this.gender = gender;
        this.admin = admin;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.postcode = postcode;
        this.location = location;
        this.street = street;
        this.streetnumber = streetnumber;
        this.status = status;
        this.role = role;
    }

    public User() {
    }

    // GETTER & SETTER


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {this.admin = admin;}
}
