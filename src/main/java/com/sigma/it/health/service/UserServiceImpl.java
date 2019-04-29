package com.sigma.it.health.service;

import com.sigma.it.health.model.User;
import com.sigma.it.health.model.UserRole;
import com.sigma.it.health.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setAdmin(registration.getAdmin());
        if (user.getAdmin()) {

            user.setRoles(Arrays.asList(new UserRole("ROLE_ADMIN")));
        }

        return userRepository.save(user);
    }


 @Override
    public User saveul(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setAdmin(registration.getAdmin());
        if (user.getAdmin()) {

            user.setRoles(Arrays.asList(new UserRole("ROLE_SUPER")));
        }

        return userRepository.save(user);
    }


    @Override
    public void sendMail(UserRegistrationDto registration) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(registration.getEmail());
        simpleMailMessage.setFrom("ghanoummotamen@gmail.com");
        simpleMailMessage.setSubject("WELCOM");
        simpleMailMessage.setText("Sigma");
        javaMailSender.send(simpleMailMessage);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }






}
