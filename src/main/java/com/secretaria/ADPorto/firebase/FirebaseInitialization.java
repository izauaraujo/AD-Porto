package com.secretaria.ADPorto.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class FirebaseInitialization {
//izau
    @PostConstruct
      public void initialization(){

          FileInputStream serviceAccount =
                  null;
          try {
              serviceAccount = new FileInputStream("./serviceAccountKey.json");

          FirebaseOptions options = new FirebaseOptions.Builder()
                  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                  .setStorageBucket("ad-porto-74a1a.appspot.com")
                  .build();

          FirebaseApp.initializeApp(options);
          Bucket bucket = StorageClient.getInstance().bucket();
          // 'bucket' is an object defined in the google-cloud-storage Java library.
          // See http://googlecloudplatform.github.io/google-cloud-java/latest/apidocs/com/google/cloud/storage/Bucket.html
          // for more details.

          } catch (Exception e) {
              e.printStackTrace();

          }
    }
}
