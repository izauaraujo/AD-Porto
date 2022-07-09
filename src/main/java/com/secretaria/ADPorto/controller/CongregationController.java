package com.secretaria.ADPorto.controller;


import com.secretaria.ADPorto.entity.Member;
import com.secretaria.ADPorto.service.CongregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/adporto")

public class CongregationController {

     private CongregationService congregationService;


     @PostMapping(value = "/createMember")
        public String postCongregationMember(@RequestBody Member member) throws ExecutionException, InterruptedException {
        return congregationService.postCongregationMember( member);
     }

     @GetMapping(value = "/readMember/{nameCongregation}")
        public ResponseEntity<List<Member>> getCongregationMember( @PathVariable String nameCongregation) throws ExecutionException, InterruptedException {
        List<Member> lista=congregationService.getActiveCongregationMember(nameCongregation);
        return ResponseEntity.ok().body(lista);
     }

     @GetMapping("/readAllMember")
     public ResponseEntity<List<Member>> getCongregationMemberGeneral( ) throws ExecutionException, InterruptedException {
        List<Member> lista=congregationService.getActiveMemberGeral();
        return ResponseEntity.ok().body(lista);
     }

    @PutMapping("/updateMember")
    public String updateCongregationMember(@RequestBody Member member) throws ExecutionException, InterruptedException {
        return congregationService.updateCongregationMember(member);
    }

    @DeleteMapping("/deleteMember/{congregationName}/{memberName}")
    public String deleteCongregationMember(@PathVariable String congregationName,@PathVariable String memberName) throws ExecutionException, InterruptedException {
        return congregationService.deleteCongregationMember(congregationName, memberName);
    }

}


