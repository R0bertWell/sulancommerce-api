package src.sulancommerce.models.entities.auth;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class RoleAuthority {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private AuthorityOld authority;
}
