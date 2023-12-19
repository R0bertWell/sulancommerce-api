package src.sulancommerce.services.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.UserDTO;
import src.sulancommerce.models.dtos.auth.SignupRequest;
import src.sulancommerce.models.entities.User;
import src.sulancommerce.repositories.UserRepository;

@Service("authService")
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user = userRepository.save(user);
        return new UserDTO(user);
    }
}
