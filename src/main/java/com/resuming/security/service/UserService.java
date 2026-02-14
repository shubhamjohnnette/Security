package com.resuming.security.service;

import com.resuming.security.model.Users;
import com.resuming.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
   @Autowired
    private AuthenticationManager authenticationManager;

    public Users register(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }


    public String verify(Users user) {
    Authentication authentication =
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if(authentication.isAuthenticated()) {
                return "Your account has been successfully verified.";
            }else  {
                return "Account verification has failed.";
            }
    }
}
