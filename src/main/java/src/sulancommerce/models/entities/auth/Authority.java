package src.sulancommerce.models.entities.auth;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private String authorityId;

    @Column(name = "authority", nullable = false)
    private String authority;
}
