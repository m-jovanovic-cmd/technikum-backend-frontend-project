package technikumbackendfrontendproject.Backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technikumbackendfrontendproject.Backend.repository.UserRepository;;

@Service
public class AuthenticationService {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    // /////////////////////////////////////////////////////////////////////////
    // Init
    // /////////////////////////////////////////////////////////////////////////

    @Autowired
    public AuthenticationService(TokenService tokenService, UserRepository userRepository) {
        this.tokenService =  tokenService;
        this.userRepository = userRepository;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////

    public String login(String username, String password) {
        var user = userRepository.findByUsernameAndPassword(username, password);

        if (user.isEmpty()) {
            //TODO Austauschen gg passendere Exception (Selber schreiben zB BadRequest)
            throw new RuntimeException();
        }

        return tokenService.generateToken(user.get());
    }
}

