package dev.adoria.gamevault.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GameVaultUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public GameVaultUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        String normalizedUsername = User.normalize(username);

        User user = userRepository.findByUsername(normalizedUsername).orElseThrow(()
            -> new UsernameNotFoundException(normalizedUsername));

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPasswordHash())
            .roles("USER")
            .build();
    }
}