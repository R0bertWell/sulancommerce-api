package src.sulancommerce.models.entities.auth;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role", unique = true)
    private String role;
}
