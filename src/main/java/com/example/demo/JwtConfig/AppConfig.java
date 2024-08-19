package com.example.demo.JwtConfig;


import com.example.demo.entities.RoleEnum;
import com.example.demo.entities.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final UtilisateurRepository ur;


//    @Bean
//    public  UserDetailsService userDetailsService(){
//        return username -> (UserDetails) ur.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



   @Bean
    public UserDetailsService userDetailsService(){
        return  new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Utilisateur utilisateur = ur.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("no user is assigned to this email :"+email));
                Collection<? extends GrantedAuthority> authorities = Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name())
                );

               return new org.springframework.security.core.userdetails.User(
                        utilisateur.getEmail(),
                        utilisateur.getPassword(),
                        authorities
                );
            }
        };
    }
}
