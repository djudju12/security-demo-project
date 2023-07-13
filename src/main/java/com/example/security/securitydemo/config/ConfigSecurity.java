package com.example.security.securitydemo.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ConfigSecurity {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
//        requestAttributeHandler.setCsrfRequestAttributeName("_csrf");

        http

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors().disable()
                .csrf().disable()
//                .cors(cors -> cors
//                        .configurationSource(request -> {
//                            CorsConfiguration config = new CorsConfiguration();
//                            config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//                            config.setAllowedMethods(Collections.singletonList("*"));
//                            config.setAllowCredentials(true);
//                            config.setAllowedHeaders(Collections.singletonList("*"));
//                            config.setExposedHeaders(List.of(SecurityConstants.JWT_HEADER));
//                            config.setMaxAge(3600L);
//                            return config;
//                        }))

//                .csrf(csrf -> csrf
//                        .csrfTokenRequestHandler(requestAttributeHandler)
//                            .ignoringRequestMatchers("/contact", "/register")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contacts", "/register", "/users").permitAll()
//                            .hasAnyAuthority("VIEWCONTACTS")
                )

//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)

                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
