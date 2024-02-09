package src.sulancommerce.models.entities.auth;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;
}
