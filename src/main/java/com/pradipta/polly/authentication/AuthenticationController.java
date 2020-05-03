package com.pradipta.polly.authentication;

import com.pradipta.polly.authentication.dto.AuthenticationHandler;
import com.pradipta.polly.authentication.dto.LoginRequest;
import com.pradipta.polly.authentication.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationHandler authenticationHandler;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        return authenticationHandler.authenticateUser(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignUpRequest request) {
        return authenticationHandler.signUpUser(request);
    }
}
