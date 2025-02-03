package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.Tweet;

@Repository
public interface tweetRepo extends JpaRepository<Tweet, Integer> {
    // add custom methods where needed 
}
