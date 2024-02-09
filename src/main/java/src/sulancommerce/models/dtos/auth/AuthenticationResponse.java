package src.sulancommerce.models.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private String username;
    private List<GrantedAuthority> authorities;
}
