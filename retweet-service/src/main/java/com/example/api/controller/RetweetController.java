package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PostMapping("/retweet/create")
    public ResponseEntity<String> Retweet(@RequestParam("postid") int postId, @RequestParam("userid") int userId) {
        return RetweetService.retweet(postId, userId); 
    }

    @DeleteMapping("/retweet/delete")
    public ResponseEntity<String> DeleteRetweet(@RequestParam("postid") int postId, @RequestParam("userid") int userId) {
        return RetweetService.deleteRetweet(postId, userId); 
    }

    @PostMapping("/retweet/like")
    public ResponseEntity<String> LikeRetweet(@RequestParam("retweetid") int retweetId, @RequestParam("userid") int userId) {
        return RetweetService.like(retweetId, userId); 
    }

    @DeleteMapping("/retweet/unlike")
    public ResponseEntity<String> UnlikeRetweet(@RequestParam("retweetid") int retweetId, @RequestParam("userid") int userId) {
        return RetweetService.unlike(retweetId, userId); 
    }

    @PatchMapping("/retweet/addlike")
    public ResponseEntity<String> IncreaseLikeCount(@RequestParam("retweetid") int retweetId, @RequestParam("userid") int userId) {
        return RetweetService.updateLikeCount(retweetId, userId); 
    }

    @PatchMapping("/retweet/removelike")
    public ResponseEntity<String> DecreaseLikeCount(@RequestParam("retweetid") int retweetId, @RequestParam("userid") int userId) {
        return RetweetService.decrementLikeCount(retweetId, userId); 
    }
}
