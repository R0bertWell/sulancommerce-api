package src.sulancommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT us FROM User us WHERE us.username like :username")
    UserDetails getUserByUsername(@Param("username") String username);

    User findFirstByUsername(String username);
}
