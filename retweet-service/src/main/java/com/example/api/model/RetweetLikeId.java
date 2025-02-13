package com.example.api.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class RetweetLikeId implements Serializable{
    private Integer retweetId; 
    private Integer likerId; 

    public RetweetLikeId() {}; 
    public RetweetLikeId(Integer retweetId, Integer likerId) {
        this.retweetId = retweetId; 
        this.likerId = likerId; 
    }
    public Integer getRetweetId() {
        return retweetId; 
    }
    public void setRetweetId(Integer newRetweetId) {
        this.retweetId = newRetweetId; 
    }
    public Integer getLikerId() {
        return likerId; 
    }
    public void setLikerId(Integer newLikerId) {
        this.likerId = newLikerId; 
    }
    @Override
    public boolean equals(Object o1) {
        if (this == o1) {return true;}
        if (o1 == null || this.getClass() != o1.getClass()) {return false;}
        RetweetLikeId that = (RetweetLikeId) o1; 
        return Objects.equals(retweetId, that.retweetId) && Objects.equals(likerId, that.likerId); 
    }
    @Override
    public int hashCode() {
        return Objects.hash(retweetId, likerId); 
    }
}
