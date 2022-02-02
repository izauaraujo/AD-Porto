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

    private static final String COLECTTION_CONGREGACAO = "CONGREGACOES";
    private static final String COLECTTION_MEMBER = "MEMBROS";


        public String postCongregationMember(Member member) throws ExecutionException, InterruptedException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLECTTION_CONGREGACAO).document(member.getCongregationName()).collection(COLECTTION_MEMBER).document(member. getMemberName()).set(member);
            return collectionApiFuture.get().getUpdateTime().toString();
        }


        public List<Member> getActiveCongregationMember(String nomeCongregacao) throws ExecutionException, InterruptedException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLECTTION_CONGREGACAO).document(nomeCongregacao).collection(COLECTTION_MEMBER).whereEqualTo("active", true).get();
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
        ApiFuture<QuerySnapshot> future = dbFirestore.collectionGroup(COLECTTION_MEMBER).whereEqualTo("active", true).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Member> listMember=new ArrayList<>();

        for (DocumentSnapshot document : documents) {
           // System.out.println(document.getId() + " => " + document.toObject(Member.class));
            Member pessoa= document.toObject(Member.class);
            listMember.add(pessoa);
        }
        return listMember;
    }
}
