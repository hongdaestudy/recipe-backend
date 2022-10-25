package com.hongdaestudy.recipebackend.user.application;

import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        return userRepository.findByEmail(email)
                .map(m -> new AccountContext(m.getEmail(), m.getPassword(), roles))
                .orElseThrow(() -> new UsernameNotFoundException("not found user"));
    }

    public static class AccountContext extends User {
        public AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }
    }
}
