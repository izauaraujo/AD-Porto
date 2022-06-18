package com.secretaria.ADPorto.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.secretaria.ADPorto.entity.User;

import java.util.concurrent.ExecutionException;

public class UserService {

    public UserRecord postUser(User user) throws ExecutionException, InterruptedException, FirebaseAuthException {

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmailUser())//"user@example.com"
                //.setEmailVerified(false)
                .setPassword(user.getPasswordUser());//"secretPassword"
                //.setPhoneNumber("+11234567890")
                //.setDisplayName("John Doe")
                //.setPhotoUrl("http://www.example.com/12345678/photo.png")
                //.setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        System.out.println("Successfully created new user: " + userRecord.getUid());



        return userRecord;
    }

}
