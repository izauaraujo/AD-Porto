package com.secretaria.ADPorto.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.secretaria.ADPorto.entity.Member;
import com.secretaria.ADPorto.entity.User;
import com.secretaria.ADPorto.service.CongregationService;
import com.secretaria.ADPorto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/adportoUser")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public String postUser(@RequestBody User user) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return userService.postUser(user);
    }

}
