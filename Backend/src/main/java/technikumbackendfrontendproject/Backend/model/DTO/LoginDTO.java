package technikumbackendfrontendproject.Backend.model.DTO;


public class LoginDTO {

    private String username;
    private String password;

    public LoginDTO(String username, String userpassword) {
        this.username = username;
        this.password = userpassword;
    }
    public LoginDTO() {

    }


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
