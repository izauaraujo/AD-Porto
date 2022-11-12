package com.secretaria.ADPorto.service;

import com.google.api.core.ApiFuture;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.secretaria.ADPorto.entity.Member;
import com.secretaria.ADPorto.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
@Service
public class CongregationService {
//izau
    private static final String COLLECTION_CONGREGATION = "CONGREGACOES";
    private static final String COLLECTION_MEMBER = "MEMBROS";


    private static final String COLLECTION_USER = "teste";
    private static final String COLLECTION_name = "teste2";
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/ad-porto-74a1a.appspot.com/o/%s?alt=media";

    public  String  postCongregationMember(Member member) throws ExecutionException, InterruptedException {

            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_CONGREGATION).document(member.getCongregationName()).collection(COLLECTION_MEMBER).document(member.getMemberName()).set(member);
            return collectionApiFuture.get().getUpdateTime().toString();
        }


       public String createUser(User user) throws ExecutionException, InterruptedException {

            //String pass = user.getUserPassword();
            //user.setUserPassword(encoder.encode(pass));

             Firestore dbFirestore = FirestoreClient.getFirestore();
             ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_USER).document(user.getUserName()).collection(COLLECTION_name).document(user.getUserName()).set(user);
             return collectionApiFuture.get().getUpdateTime().toString();
       }





        public List<Member> getActiveCongregationMember(String nameCongregation) throws ExecutionException, InterruptedException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_CONGREGATION).document(nameCongregation).collection(COLLECTION_MEMBER).whereEqualTo("situation","ativo").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Member> listMember=new ArrayList<>();

            for (DocumentSnapshot document : documents) {
                Member pessoa= document.toObject(Member.class);
                listMember.add(pessoa);
            }
            return listMember;
        }


        public List<Member> getActiveMemberGeral() throws ExecutionException, InterruptedException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = dbFirestore.collectionGroup(COLLECTION_MEMBER).whereEqualTo("situation", "ativo").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Member> listMember=new ArrayList<>();

            for (DocumentSnapshot document : documents) {
                // System.out.println(document.getId() + " => " + document.toObject(Member.class));
                Member pessoa= document.toObject(Member.class);
                listMember.add(pessoa);
            }
            return listMember;
        }


        public String updateCongregationMember(Member member) throws ExecutionException, InterruptedException {
             Firestore dbFirestore = FirestoreClient.getFirestore();
             ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_CONGREGATION).document(member.getCongregationName()).collection(COLLECTION_MEMBER).document(member. getMemberName()).set(member);

             return collectionApiFuture.get().getUpdateTime().toString();
        }


        public String deleteCongregationMember(String congregationName, String memberName) throws ExecutionException, InterruptedException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_CONGREGATION).document(congregationName).collection(COLLECTION_MEMBER).document(memberName).delete();

            return collectionApiFuture.get().getUpdateTime().toString();
        }


    // usado para fazer upload de um arquivo
    private static String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("ad-porto-74a1a.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./serviceAccountKey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    // usado para converter MultipartFile em arquivo
    private static File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    // usado para obter extensão de um arquivo carregado
    private static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    public static Object uploadFoto(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(getExtension(fileName));  // to generated random string values for file name.

            File file = convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
           String TEMP_URL = uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return  TEMP_URL;                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return  e ;
        }

    }

    public static Object downloadFoto(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(getExtension(fileName));     // to set random strinh for destination file name
        String destFilePath = "Z:\\New folder\\" + destFileName;                                    // to set destination file path

        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("path of JSON with genarated private key"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("your bucket name", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return ("Successfully Downloaded!");
    }


}