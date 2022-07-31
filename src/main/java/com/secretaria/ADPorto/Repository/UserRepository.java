package com.secretaria.ADPorto.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.secretaria.ADPorto.entity.User;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutionException;
public class UserRepository {
    private static final String COLLECTION_USER = "USUARIOS";

    public static User getUser(String userName) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFirestore.collection(COLLECTION_USER).document(userName);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
       // User user =  null;
        User user = document.toObject(User.class);
        return user;
    }
 }

