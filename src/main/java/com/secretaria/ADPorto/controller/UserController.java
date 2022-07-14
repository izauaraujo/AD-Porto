package com.secretaria.ADPorto.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.secretaria.ADPorto.entity.Member;
import com.secretaria.ADPorto.entity.User;
import com.secretaria.ADPorto.service.CongregationService;
import com.secretaria.ADPorto.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
//izau
@RestController
public class UserController {
    private CongregationService congregationService;
    private UserService userService;

    @PostMapping("/createUser")
    public UserRecord postUser(@RequestBody User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return userService.createUser( user);
    }

    @PostMapping("/loginUser")
    public String getUser(@RequestBody User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return userService.TokenUser(user);
    }
}
