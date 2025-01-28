package com.vishalpal555.kafkaServer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
@RequestMapping()
public class RootController {
    @PutMapping("/updateLocation")
    public ResponseEntity<?> updateLocation(){
        SecureRandom secureRandom = new SecureRandom();
        return ResponseEntity.ok(String.format("%s : %s", secureRandom.nextInt(100), secureRandom.nextInt(100)));
    }
}
