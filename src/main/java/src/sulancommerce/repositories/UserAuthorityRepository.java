package src.sulancommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.auth.Authority;
import src.sulancommerce.models.entities.auth.UserAuthority;
import src.sulancommerce.models.entities.auth.UserAuthorityPK;

import java.util.List;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityPK> {
    @Query("SELECT ua.userAuthorityPK.authority FROM UserAuthority ua WHERE ua.userAuthorityPK.user.username = :username")
    List<Authority> getUserAuthorities(@Param("username") String username);
}
