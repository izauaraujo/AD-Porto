package com.secretaria.ADPorto.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.secretaria.ADPorto.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
//izau
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

   // public static String findUser(User user) throws FirebaseAuthException {
   //     UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(user.getEmailUser());
        // See the UserRecord reference doc for the contents of userRecord.
        //System.out.println("Successfully fetched user data: " + userRecord.getEmail());
    //    return userRecord.getUid();
    //}

    public static String TokenUser(User user) throws FirebaseAuthException {
        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(user.getEmailUser());
        String uid = userRecord.getUid();
        Map<String, Object> additionalClaims = new HashMap<String, Object>();
        if (user.getRoleUser()=="Admin") {
            additionalClaims.put("Admin", true);
        }else {
            additionalClaims.put("User", true);
        }
        String customToken = FirebaseAuth.getInstance().createCustomToken(uid, additionalClaims);

    return customToken;
    }
}
