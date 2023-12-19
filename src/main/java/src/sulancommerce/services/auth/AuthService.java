package src.sulancommerce.services.auth;

import src.sulancommerce.models.dtos.UserDTO;
import src.sulancommerce.models.dtos.auth.SignupRequest;
import src.sulancommerce.repositories.UserRepository;

public interface AuthService {

    UserDTO createUser(SignupRequest signupRequest);
}
