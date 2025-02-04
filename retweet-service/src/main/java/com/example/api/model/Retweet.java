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
@Table(name = "retweets")
public class Retweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Column(name="author_id")
    private int author_id; 

    @Column(name="post_id")
    private int post_id; 

    @Column(name="title")
    private String title; // remember to add lengths for this  

    @Column(name="body")
    private String body; // remember to add lengths for this 

    @Column(name="retweeter_id")
    private int retweeter_id; 

    @Column(name="likes", nullable = false)
    private int likes = 0; 

    @Column(name="comment_count", nullable = false)
    private int comment_count = 0; 

    @Column(name="post_date", nullable = false)
    private LocalDateTime post_date; 

    public Retweet(Integer id, int author_id, int post_id, String title, String body, int retweeter_id, int likes, int comment_count, LocalDateTime post_date) {
        this.id = id; 
        this.author_id = author_id; 
        this.post_id = post_id; 
        this.title = title; 
        this.body = body; 
        this.retweeter_id = retweeter_id; 
        this.likes = likes; 
        this.comment_count = comment_count; 
        this.post_date = post_date; 
    }

    public Integer getId() {
        return id; 
    }
    public int getPost_id() {
        return post_id; 
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
    public int getRetweeter() {
        return retweeter_id; 
    }
    public int getLikes() {
        return likes; 
    }
    public int getCommentCount() {
        return comment_count; 
    }
    public LocalDateTime getDate() {
        return post_date; 
    }

    public void addLike() {
        this.likes++; 
    }

    public void addCommentCount() {
        this.comment_count++; 
    }
}
