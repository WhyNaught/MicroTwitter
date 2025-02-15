package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api.model.Tweet;

@Repository
public interface tweetRepo extends JpaRepository<Tweet, Integer> {
    @Query(value="SELECT * FROM posts WHERE post_id = ?1 AND retweeter_id = ?2", nativeQuery = true)
    public Tweet getByAuthorIdAndPostId(int original_post_id, int retweeter_id); 
}
