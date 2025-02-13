package com.example.api.model;

import jakarta.persistence.*; 
import lombok.Data;

@Data
@Entity
@Table(name="retweetlikes")
public class RetweetLikeObject {
    @EmbeddedId
    private RetweetLikeId id; 

    public RetweetLikeObject() {}; 
    public RetweetLikeObject(Integer retweetId, Integer likerId) {
        this.id = new RetweetLikeId(retweetId, likerId); 
    }
    public RetweetLikeId getId() {
        return id; 
    }
    public void setId(RetweetLikeId newId) {
        this.id = newId; 
    }
    public Integer getRetweetId() {
        return id.getRetweetId(); 
    }
    public Integer getLikerId() {
        return id.getLikerId(); 
    }
}
