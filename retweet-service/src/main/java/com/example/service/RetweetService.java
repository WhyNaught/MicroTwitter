package com.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Retweet;
import com.example.repository.*;

@Service
public class RetweetService {
    @Autowired
    private retweetRepo retweetRepo; 

    @Autowired
    private tweetRepo tweetRepo; 

    public Retweet retweet(int original_post_id) {
        
        Retweet.setPost_date(LocalDateTime.now());
        return retweetRepo.save(Retweet); 
    }
}