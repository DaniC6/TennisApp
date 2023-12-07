package com.DaniC.TennisApp.controllers;

import com.DaniC.TennisApp.payload.request.SigninRequest;
import com.DaniC.TennisApp.payload.request.SignupRequest;
import com.DaniC.TennisApp.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(
            description = "POST ENDPOINT FOR USER SIGNUP",
            summary = "Questo è il sommario",
            responses ={
                    @ApiResponse(description = "User created successfully", responseCode = "201"),
                    @ApiResponse(description = "Bad ReQuest", responseCode = "400"),
                    @ApiResponse(description = "E' andato tutto a scatafascio!", responseCode = "500")
            }
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest signupRequest){
        return authenticationService.signup(signupRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SigninRequest signinRequest){
        return authenticationService.signin(signinRequest);
    }

}
