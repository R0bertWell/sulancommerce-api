package src.sulancommerce.services.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.entities.auth.Authority;
import src.sulancommerce.models.entities.auth.User;
import src.sulancommerce.repositories.UserAuthorityRepository;
import src.sulancommerce.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAuthorityRepository userAuthorityRepository;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserAuthorityRepository userAuthorityRepository,
                                  UserRepository userRepository) {
        this.userAuthorityRepository = userAuthorityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found", null);
        List<Authority> userAuthorities = this.userAuthorityRepository.getUserAuthorities(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        userAuthorities.forEach( auth -> authorities.add(new SimpleGrantedAuthority(auth.getAuthority())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
