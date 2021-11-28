package com.ironhack.edgeservice.controller.implementations;

import com.ironhack.edgeservice.model.UserEntity;
import com.ironhack.edgeservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public void signIn(@RequestBody UserEntity user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
