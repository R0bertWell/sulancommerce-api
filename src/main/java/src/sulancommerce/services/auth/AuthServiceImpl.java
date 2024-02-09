package src.sulancommerce.services.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.UserDTO;
import src.sulancommerce.models.dtos.auth.SignupRequest;
import src.sulancommerce.models.entities.auth.Authority;
import src.sulancommerce.models.entities.auth.User;
import src.sulancommerce.models.entities.auth.UserAuthority;
import src.sulancommerce.models.entities.auth.UserAuthorityPK;
import src.sulancommerce.repositories.AuthorityRepository;
import src.sulancommerce.repositories.UserAuthorityRepository;
import src.sulancommerce.repositories.UserRepository;

@Service("authService")
public class AuthServiceImpl implements AuthService{
    private final AuthorityRepository authorityRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthorityRepository authorityRepository,
                           UserAuthorityRepository userAuthorityRepository,
                           UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        if(userRepository.userExists(signupRequest.getUsername()) > 0){
            return null;
        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user = userRepository.save(user);

        UserAuthority userAuthority = new UserAuthority();
        Authority authority = authorityRepository.getNormalUserAuthority();
        UserAuthorityPK userAuthorityPK = new UserAuthorityPK(user, authority);
        userAuthority.setUserAuthorityPK(userAuthorityPK);
        userAuthorityRepository.save(userAuthority);

        return new UserDTO(user);
    }
}
