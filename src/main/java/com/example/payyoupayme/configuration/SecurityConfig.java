package com.example.payyoupayme.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/admin/**").hasRole("ADMIN");
            //auth.requestMatchers("/user").hasAnyRole("ADMIN", "USER");
            auth.requestMatchers("/signup", "/", "/login", "/h2-console/**").permitAll();
            auth.anyRequest().authenticated();
        }).formLogin(Customizer.withDefaults()).build();
    }

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                )
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
                    auth.requestMatchers(new AntPathRequestMatcher("/")).permitAll(); //tout le monde a accès a ce chemin sans être connecter
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/me/user/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/signup", "/public/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") //redirige vers la page d'accueil après s'être déconnecter
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                );

        return http.build();
    }*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
}
