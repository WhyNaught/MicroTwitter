package com.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.model.Retweet;
import com.example.api.model.Tweet; 
import com.example.repository.*;

@Service
public class RetweetService {
    @Autowired
    private retweetRepo retweetRepo; 

    @Autowired
    private tweetRepo tweetRepo; 

    public ResponseEntity<String> retweet(int original_post_id, int retweeter_id) {
        if (retweetRepo.getByAuthorIdAndPostId(original_post_id, retweeter_id) != null) {
            return new ResponseEntity<>("You have already retweeted this post!", HttpStatus.BAD_REQUEST); 
        }

        Tweet originalTweet = tweetRepo.findById(original_post_id).get();  
        LocalDateTime retweet_date = LocalDateTime.now(); 
        Retweet newRetweet = new Retweet(null, originalTweet.getAuthor(), originalTweet.getId(), originalTweet.getTitle(), originalTweet.getBody(), retweeter_id, 0, 0, retweet_date); 
        retweetRepo.save(newRetweet); 
        return new ResponseEntity<>("Retweeted Succesfully!", HttpStatus.CREATED); 
    }
}