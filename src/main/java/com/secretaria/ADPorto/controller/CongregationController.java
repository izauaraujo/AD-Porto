package com.secretaria.ADPorto.controller;


import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.secretaria.ADPorto.entity.Member;
import com.secretaria.ADPorto.service.CongregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/adporto")

public class CongregationController {

     @Autowired
     private CongregationService congregationService;

     @PostMapping("/postmember")
        public String postCongregationMember(@RequestBody Member member) throws ExecutionException, InterruptedException {
        return congregationService.postCongregationMember( member);
     }

     @GetMapping("/getmember/{nomeCongregacao}")
        public ResponseEntity<List<Member>> getCongregationMember( @PathVariable String nomeCongregacao) throws ExecutionException, InterruptedException {
        List<Member> lista=congregationService.getActiveCongregationMember(nomeCongregacao);
        return ResponseEntity.ok().body(lista);
     }

     @GetMapping("/getmembergeral")
     public ResponseEntity<List<Member>> getCongregationMemberGeral( ) throws ExecutionException, InterruptedException {
        List<Member> lista=congregationService.getActiveMemberGeral();
        return ResponseEntity.ok().body(lista);
     }

    @PutMapping("/updatemember")
    public String updateCongregationMember(@RequestBody Member member) throws ExecutionException, InterruptedException {
        return congregationService.updateCongregationMember(member);
    }

    @DeleteMapping("/deletemember")
    public String deleteCongregationMember(@RequestBody Member member) throws ExecutionException, InterruptedException {
        return congregationService.deleteCongregationMember(member);
    }

}
