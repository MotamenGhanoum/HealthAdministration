package com.sigma.it.health.service;

import com.sigma.it.health.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService  {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

    void sendMail(UserRegistrationDto registration);

    void updatePassword(String password, Long userId);


    User saveul(UserRegistrationDto userDto);




}
