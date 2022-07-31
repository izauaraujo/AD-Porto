package com.secretaria.ADPorto.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.secretaria.ADPorto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
@Service
public class UserService {
//izau

    private static final String COLLECTION_USER = "USUARIOS";

    @Autowired
    private PasswordEncoder encoder;

    public String createUser(User user) throws InterruptedException, ExecutionException {

       String pass = user.getPassword();
       user.setPassword(encoder.encode(pass));

       Firestore dbFirestore = FirestoreClient.getFirestore();
       ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_USER).document(user.getUserName()).set(user);;
        return  collectionApiFuture.get().getUpdateTime().toString();

   }
}
