package com.example.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="posts")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id; 
    
    @Column(name="author_id")
    private int author_id; 

    @Column(name="title")
    private String title;
    
    @Column(name="body")
    private String body;
    
    @Column(name="likes")
    private int likes = 0; 

    @Column(name="comment_count")
    private int comment_count = 0;
    
    @Column(name="post_date")
    private LocalDateTime post_date; 

    public Tweet() {
        
    }
    
    public Tweet(int id, int author_id, String title, String body, int likes, int comment_count, LocalDateTime post_date) {
        this.id = id; 
        this.author_id = author_id; 
        this.title = title; 
        this.body = body; 
        this.likes = likes; 
        this.comment_count = comment_count; 
        this.post_date = post_date; 
    }

    public int getId() {
        return id; 
    }

    public int getAuthor() {
        return author_id; 
    }

    public String getTitle() {
        return title; 
    }
    public String getBody() {
        return body; 
    }
    public int getLikes() {
        return likes; 
    }
    public int getCommentCount() {
        return comment_count; 
    }
    public LocalDateTime getPostDate() {
        return post_date; 
    }
}
