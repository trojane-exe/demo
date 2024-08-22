package com.example.demo.web.auth;

import com.example.demo.JwtConfig.JwtService;
import com.example.demo.entities.RoleEnum;
import com.example.demo.entities.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Data
@RequiredArgsConstructor

public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository ur;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setAdresse(request.getAdresse());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setPassword(passwordEncoder.encode(request.getPassword()));
        if(request.getRole()==null){
            utilisateur.setRole(RoleEnum.User);
        }
        else{
        utilisateur.setRole(RoleEnum.valueOf(request.getRole()));}
        ur.save(utilisateur);

        String jwtToken = jwtService.generateToken(null, new org.springframework.security.core.userdetails.User(
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()))
        ));

        return new AuthenticationResponse(jwtToken);

    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Utilisateur utilisateur = ur.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwtToken = jwtService.generateToken(null, new org.springframework.security.core.userdetails.User(
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()))
        ));

        return new AuthenticationResponse(jwtToken);
    }
}
