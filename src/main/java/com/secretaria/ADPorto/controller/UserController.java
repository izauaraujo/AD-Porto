package com.secretaria.ADPorto.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.secretaria.ADPorto.entity.User;
import com.secretaria.ADPorto.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.ExecutionException;

public class UserController {

    private UserService userService;

    @PostMapping("/createUser")
    public UserRecord postUser(@RequestBody User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return userService.createUser( user);
    }


    @GetMapping("/findUser")
    public UserRecord getUser(@RequestBody User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return userService.findUser( user);
    }

}
