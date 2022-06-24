package com.secretaria.ADPorto.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.secretaria.ADPorto.entity.User;

public class UserService {

    public static UserRecord createUser(User user) throws FirebaseAuthException {
        // [START create_user]
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmailUser())
                //.setEmailVerified(false)
                .setPassword(user.getPasswordUser())
                //.setPhoneNumber("+11234567890")
                //.setDisplayName("John Doe")
                //.setPhotoUrl("http://www.example.com/12345678/photo.png")
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        System.out.println("Successfully created new user: " + userRecord.getUid());
        // [END create_user]


        return null;
    }

}
