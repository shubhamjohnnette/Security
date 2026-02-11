package com.resuming.security.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        to desable csrf
// this all support builder pattern
        http.csrf(customizer -> customizer.disable());
        // Any authenticated request can access
        http.authorizeHttpRequests(request -> request.anyRequest());
        // login with default page
        http.formLogin(Customizer.withDefaults());
        // if need to see responce body without page
        http.httpBasic(Customizer.withDefaults());
//        to make stateless if you need to generatesession id every time
        http.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build(); //will return SecurityFilterChain obj
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager();
    }
}
