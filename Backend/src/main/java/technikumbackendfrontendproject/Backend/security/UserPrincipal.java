package technikumbackendfrontendproject.Backend.security;

public class UserPrincipal {
    
    private final Long userID;
    private final String username;
    private final boolean admin;

    public UserPrincipal(Long userId, String username, boolean admin) {
        this.userID = userId;
        this.username = username;
        this.admin = admin;
    }

    public Long getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public boolean isAdmin() {
        return admin;
    }
}
