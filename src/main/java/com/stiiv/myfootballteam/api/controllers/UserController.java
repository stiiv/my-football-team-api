package com.stiiv.myfootballteam.api.controllers;

import com.stiiv.myfootballteam.api.models.User;
import com.stiiv.myfootballteam.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody @Valid User user) {
        userRepository.save(user);
        return userRepository.findByUsername(user.getUsername());
    }

}
