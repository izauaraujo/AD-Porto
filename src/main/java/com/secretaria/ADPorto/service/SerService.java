package com.secretaria.ADPorto.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.secretaria.ADPorto.entity.Member;
import com.secretaria.ADPorto.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class SerService {
//izau
    private static final String COLLECTION_USER = "teste";
    private static final String COLLECTION_name = "teste2";
    //private PasswordEncoder encoder;

    public String createUser(User user) throws ExecutionException, InterruptedException {

       //String pass = user.getUserPassword();
       //user.setUserPassword(encoder.encode(pass));

       Firestore dbFirestore = FirestoreClient.getFirestore();
       ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_USER).document(user.getUserName()).collection(COLLECTION_name).document(user.getUserName()).set(user);;
       return collectionApiFuture.get().getUpdateTime().toString();
   }
}
