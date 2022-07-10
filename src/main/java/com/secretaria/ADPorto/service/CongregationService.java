package com.secretaria.ADPorto.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.secretaria.ADPorto.entity.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class CongregationService {

    private static final String COLLECTION_CONGREGATION = "CONGREGACOES";
    private static final String COLLECTION_MEMBER = "MEMBROS";

        public String postCongregationMember(Member member) throws ExecutionException, InterruptedException {

            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_CONGREGATION).document(member.getCongregationName()).collection(COLLECTION_MEMBER).document(member.getMemberName()).set(member);
            return collectionApiFuture.get().getUpdateTime().toString();
        }


        public List<Member> getActiveCongregationMember(String nameCongregation) throws ExecutionException, InterruptedException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLLECTION_CONGREGATION).document(nameCongregation).collection(COLLECTION_MEMBER).whereEqualTo("active", true).get();
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
            ApiFuture<QuerySnapshot> future = dbFirestore.collectionGroup(COLLECTION_MEMBER).whereEqualTo("active", true).get();
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
}