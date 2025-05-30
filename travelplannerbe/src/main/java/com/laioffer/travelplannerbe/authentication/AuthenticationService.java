package com.laioffer.travelplannerbe.authentication;

import com.laioffer.travelplannerbe.model.UserEntity;
import com.laioffer.travelplannerbe.repository.UserRepository;
import com.laioffer.travelplannerbe.security.JwtHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHandler jwtHandler;


    public AuthenticationService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtHandler jwtHandler
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHandler = jwtHandler;
    }

    // the logic here is login automatically after register
    public String register(String username, String password) {
        // user already exists throw exception
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistException();
        }

        // create and save UserEntity
        UserEntity userEntity = new UserEntity(null, username, passwordEncoder.encode(password));
        userRepository.save(userEntity);

        // login
        return login(username, password);
    }

    public String login(String username, String password) {
        return authAndGenerateToken(username, password);
    }

    private String authAndGenerateToken(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtHandler.generateToken(username);
    }
}
