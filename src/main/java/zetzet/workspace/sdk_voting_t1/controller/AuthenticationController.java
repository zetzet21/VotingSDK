package zetzet.workspace.sdk_voting_t1.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zetzet.workspace.sdk_voting_t1.dto.request.LoginRequest;
import zetzet.workspace.sdk_voting_t1.dto.request.RegistrationRequest;
import zetzet.workspace.sdk_voting_t1.dto.response.TokenResponse;
import zetzet.workspace.sdk_voting_t1.service.AuthenticationService;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok()
                .body(authenticationService.register(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok()
                .body(authenticationService.login(loginRequest));
    }
}
