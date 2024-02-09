package src.sulancommerce.models.entities.auth;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class AuthorityOld {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private String authorityId;

    @Column(name = "sn_admin", nullable = false)
    private Boolean snAdmin;

    @Column(name = "sn_company_account", nullable = false)
    private Boolean snCompanyAccount;

    @Column(name = "sn_user_account", nullable = false)
    private Boolean snUserAccount;
}