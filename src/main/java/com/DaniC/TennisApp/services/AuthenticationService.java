package com.DaniC.TennisApp.services;


import com.DaniC.TennisApp.entities.Authority;
import com.DaniC.TennisApp.entities.User;
import com.DaniC.TennisApp.payload.request.SigninRequest;
import com.DaniC.TennisApp.payload.request.SignupRequest;
import com.DaniC.TennisApp.payload.response.AuthenticationResponse;
import com.DaniC.TennisApp.repositories.AuthorityRepository;
import com.DaniC.TennisApp.repositories.UserRepository;
import com.DaniC.TennisApp.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> signup(SignupRequest request) {
        if(userRepository.existsByEmailOrUsername(request.getEmail(), request.getUsername()))
            return new ResponseEntity<String>("Username or email already in use", HttpStatus.BAD_REQUEST);
        Authority a = authorityRepository.findByDefaultAuthorityTrue();
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities( Collections.singleton(a))
                .build();
        userRepository.save(user);
        log.info("### "+user.toString());
        return new ResponseEntity<String>("User successfully registered",HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> signin(SigninRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->  new BadCredentialsException ("Bad credentials"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Bad credentials");

        boolean isGuest = ArrayUtils.contains(authorities(user.getAuthorities()), "ROLE_GUEST");

        if(!user.isEnabled() && isGuest){
            return new ResponseEntity("Please check your mail and activate your account", HttpStatus.UNAUTHORIZED);


        }

        String jwtToken = jwtService.generateToken(user, user.getId());
        return new ResponseEntity( AuthenticationResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .authorities(authorities(user.getAuthorities()))
                .token(jwtToken)
                .build(),
                HttpStatus.OK);
    }

    private String[] authorities(Collection<? extends GrantedAuthority> auths){
        return auths.stream().map(a -> a.getAuthority())
                .toArray(String[]::new);
    }

}
