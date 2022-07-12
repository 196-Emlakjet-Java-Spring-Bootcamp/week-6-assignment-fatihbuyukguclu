package com.patika.userservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()){
            log.error("User not found in the DB");
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        log.info("User found : {}",user.getUsername());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);

    }

    public void createUser(UserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .build();
        userRepository.save(user);
        log.info("New User Saved {}",user.getUsername());
    }

    public UsernamePasswordAuthenticationToken signUser(UserRequest userRequest){
        var user = (org.springframework.security.core.userdetails.User) loadUserByUsername(userRequest.getUsername());
        log.info(user.toString());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );

        return (UsernamePasswordAuthenticationToken) authentication.getCredentials();
    }
}
