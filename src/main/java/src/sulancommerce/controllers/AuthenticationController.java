package src.sulancommerce.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import src.sulancommerce.models.dtos.UserDTO;
import src.sulancommerce.models.dtos.auth.AuthenticationRequest;
import src.sulancommerce.models.dtos.auth.AuthenticationResponse;
import src.sulancommerce.models.dtos.auth.SignupRequest;
import src.sulancommerce.services.auth.AuthService;
import src.sulancommerce.services.jwt.UserDetailsServiceImpl;
import src.sulancommerce.utils.JwtUtil;

import java.io.IOException;

@RestController
public class AuthenticationController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthService authService,
                                    AuthenticationManager authenticationManager,
                                    UserDetailsServiceImpl userDetailsService,
                                    JwtUtil jwtUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authentication")
    public AuthenticationResponse createAuthToken(@RequestBody AuthenticationRequest authenticationRequest,
                                                  HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        String username = authenticationRequest.getUsername();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or Password");
        } catch (DisabledException dEx) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created, Register User first.");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt, username);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest){
        UserDTO createUser = authService.createUser(signupRequest);
        if(createUser == null)
            return new ResponseEntity<>("User is not created, try again later.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }
}
