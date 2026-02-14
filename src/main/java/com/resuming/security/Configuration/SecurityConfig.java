package com.resuming.security.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        to desable csrf
// this all support builder pattern
        http.csrf(customizer -> customizer.disable());
        // Any authenticated request can access
//        http.authorizeHttpRequests(request ->
//                request.requestMatchers("register","login").permitAll().anyRequest());
        // login with default page
        http.formLogin(Customizer.withDefaults());
        // if need to see responce body without page
        http.httpBasic(Customizer.withDefaults());
//        to make stateless if you need to generatesession id every time
//        http.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build(); //will return SecurityFilterChain obj
    }



//    @Bean
//    public UserDetailsService userDetailsService(){
//
////        these are hard coded as this wont work
//        UserDetails user = User.withDefaultPasswordEncoder().username("shubh")
//                .password("Shubh@123").roles("USER").build();
//        UserDetails user2 = User.withDefaultPasswordEncoder().username("Shikha")
//                .password("Shubh@123").roles("ADMIN").build();
////        when you want to filter with your db need this object
//        return new InMemoryUserDetailsManager(user,user2);
//    }

//verify data from dbms
@Bean
public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
    provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    return provider;

}

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
   return config.getAuthenticationManager();
}

}
