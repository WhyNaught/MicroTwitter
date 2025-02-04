package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.*;

@RestController
public class RetweetController {

    @Autowired
    private RetweetService RetweetService; 
    
    public RetweetController(RetweetService RetweetService) {
        this.RetweetService = RetweetService; 
    } 

    // consider using requestbody and creating a new object for that purpose?
    @PostMapping("/retweet/create")
    public ResponseEntity<String> Retweet(@RequestParam("postid") int postId, @RequestParam("userid") int userId) {
        return RetweetService.retweet(postId, userId); 
    }
}
