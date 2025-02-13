package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.RetweetLikeObject;
import com.example.api.model.RetweetLikeId;

public interface retweetLikeRepo extends JpaRepository<RetweetLikeObject, RetweetLikeId>{
    
}  
