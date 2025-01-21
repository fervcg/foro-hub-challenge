package com.alura.ForoHubAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForoHubAPI.domain.model.User;
import com.alura.ForoHubAPI.dto.user.AuthenticationUserDTO;
import com.alura.ForoHubAPI.infrastructure.security.JwtTokenDTO;
import com.alura.ForoHubAPI.infrastructure.security.TokenService;
import org.springframework.security.core.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JwtTokenDTO> authenticateUser(@RequestBody @Valid AuthenticationUserDTO authenticationUserData){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(authenticationUserData.email(), authenticationUserData.password());

        var authenticatedUser = authenticationManager.authenticate(authenticationToken).getPrincipal();
        
        var JwtToken = tokenService.generateToken((User)authenticatedUser);

        return ResponseEntity.ok(new JwtTokenDTO(JwtToken));
        
    }
}
