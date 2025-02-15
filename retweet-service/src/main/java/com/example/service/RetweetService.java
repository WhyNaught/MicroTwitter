package com.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.model.Retweet;
import com.example.api.model.RetweetLikeId;
import com.example.api.model.RetweetLikeObject;
import com.example.api.model.Tweet; 
import com.example.repository.*;

@Service
public class RetweetService {
    @Autowired
    private retweetRepo retweetRepo; 

    @Autowired
    private tweetRepo tweetRepo; 

    @Autowired
    private retweetLikeRepo retweetLikeRepo; 

    public ResponseEntity<String> retweet(int original_post_id, int retweeter_id) {
        if (tweetRepo.getByAuthorIdAndPostId(original_post_id, retweeter_id) != null) {
            return new ResponseEntity<>("You have already retweeted this post!", HttpStatus.BAD_REQUEST); 
        }
        Tweet originalTweet = tweetRepo.findById(original_post_id).get();  
        LocalDateTime retweet_date = LocalDateTime.now(); 
        Retweet newRetweet = new Retweet(null, originalTweet.getAuthor(), originalTweet.getId(), originalTweet.getTitle(), originalTweet.getBody(), retweeter_id, 0, 0, retweet_date); 
        retweetRepo.save(newRetweet); 
        return new ResponseEntity<>("Retweeted Succesfully!", HttpStatus.CREATED); 
    }

    public ResponseEntity<String> deleteRetweet(int original_post_id, int retweeter_id) {
        Retweet result = retweetRepo.getByAuthorIdAndPostId(original_post_id, retweeter_id); 
        if (result == null) {
            return new ResponseEntity<>("No such retweet exists", HttpStatus.BAD_REQUEST); 
        } else {
            retweetRepo.deleteById(result.getId());
            return new ResponseEntity<>("Deleted retweet succesfully!", HttpStatus.OK); 
        }
        
    }

    public boolean checkRetweetLikeRepo(int retweetId, int userId) {
        RetweetLikeId newId = new RetweetLikeId(retweetId, userId); 
        return (retweetLikeRepo.findById(newId).isPresent()); 
    }

    public ResponseEntity<String> like(Integer retweet_id, Integer user_id) {
        if (checkRetweetLikeRepo(retweet_id, user_id)) {
            return new ResponseEntity<>("You have already liked this post!", HttpStatus.CONFLICT); 
        } else {
            RetweetLikeObject newLike = new RetweetLikeObject(retweet_id, user_id);
            retweetLikeRepo.save(newLike); 
            return new ResponseEntity<>("Added like to retweetLike table succesfully!", HttpStatus.CREATED); 
        }
    }

    public ResponseEntity<String> updateLikeCount(Integer retweet_id, Integer user_id) {
        if (checkRetweetLikeRepo(retweet_id, user_id)) {
            return new ResponseEntity<>("You have already liked this post!", HttpStatus.CONFLICT); 
        } else {
            retweetRepo.likeRetweet(retweet_id); 
            return new ResponseEntity<>("Updated table like count succesfully!", HttpStatus.OK); 
        }
    }

    public ResponseEntity<String> unlike(Integer retweet_id, Integer user_id) {
        if (!checkRetweetLikeRepo(retweet_id, user_id)) {
            return new ResponseEntity<>("You have not liked this post!", HttpStatus.CONFLICT); 
        } else {
            retweetLikeRepo.deleteById(new RetweetLikeId(retweet_id, user_id));
            return new ResponseEntity<>("Deleted like from retweetlikes succesfully!", HttpStatus.OK); 
        }
    }

    public ResponseEntity<String> decrementLikeCount(Integer retweet_id, Integer user_id) {
        if (!checkRetweetLikeRepo(retweet_id, user_id)) {
            return new ResponseEntity<>("You have not liked this post!", HttpStatus.CONFLICT); 
        } else {
            retweetRepo.unlikeRetweet(retweet_id); 
            return new ResponseEntity<>("Decreased table like count succesfully!", HttpStatus.OK); 
        }    
    }
}