package com.example.repository; 

import com.example.api.model.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface retweetRepo extends JpaRepository<Retweet, Integer> {
    @Query(value="SELECT * FROM retweets WHERE post_id = ?1 AND retweeter_id = ?2", nativeQuery = true)
    public Retweet getByAuthorIdAndPostId(int original_post_id, int retweeter_id); 

    @Query(value="UPDATE retweets SET likes=likes + 1 WHERE post_id=?1", nativeQuery = true)
    public ResponseEntity<String> likeRetweet(int post_id); 

    @Query(value="UPDATE retweets SET likes=likes - 1 WHERE post_id=?1", nativeQuery = true)
    public ResponseEntity<String> unlikeRetweet(int post_id); 
}