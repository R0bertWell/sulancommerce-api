package src.sulancommerce.models.entities.auth;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_authority")
public class UserAuthority {
    @EmbeddedId
    private UserAuthorityPK userAuthorityPK;
}
