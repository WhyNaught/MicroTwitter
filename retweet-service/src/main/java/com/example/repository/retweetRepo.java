package com.example.repository; 

import com.example.api.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface retweetRepo extends JpaRepository<Retweet, Integer> {
    // add custom methods if needed 
}