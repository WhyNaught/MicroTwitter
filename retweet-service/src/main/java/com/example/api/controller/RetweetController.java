package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Retweet;

import com.example.service.*;

@RestController
public class RetweetController {

    @Autowired
    private RetweetService RetweetService; 
    
    public RetweetController(RetweetService RetweetService) {
        this.RetweetService = RetweetService; 
    } 

    // Create API endpoints here 
    @PostMapping("/retweet/create")
    public ResponseEntity<Retweet> Retweet(@RequestBody Retweet request) {
        return ResponseEntity.ok().body(RetweetService.retweet(request)); 
    }
}
