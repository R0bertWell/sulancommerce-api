package src.sulancommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.auth.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("SELECT a FROM Authority a WHERE a.authority = 'USER_ROLE'")
    Authority getNormalUserAuthority();
}
