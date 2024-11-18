package ru.pavel16649.Services.Jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.pavel16649.Domain.User;
import ru.pavel16649.Config.UserDetailsImpl;
import ru.pavel16649.Repositories.JpaUserRepository;

import java.util.Optional;


public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private JpaUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}

