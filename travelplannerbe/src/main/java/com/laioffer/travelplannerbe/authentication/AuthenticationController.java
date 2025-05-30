package com.laioffer.travelplannerbe.authentication;


import com.laioffer.travelplannerbe.model.LoginRequest;
import com.laioffer.travelplannerbe.model.LoginResponse;
import com.laioffer.travelplannerbe.model.RegisterRequest;
import com.laioffer.travelplannerbe.authentication.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse register(@RequestBody RegisterRequest body) {
        String token = authenticationService.register(body.username(), body.password());
        return new LoginResponse(token);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest body) {
        String token = authenticationService.login(body.username(), body.password());
        return new LoginResponse(token);
    }
}
