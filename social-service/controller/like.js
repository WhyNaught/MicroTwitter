const express = require('express'); 
const router = express.Router(); 
const db = require('../db');
const extractToken = require('./extractToken'); // make sure to test if this works, and to add it to follow.js
require('dotenv').config();

router.get('/social/checkLiked/:postid', (req, res) => {
    const {postid} = req.params; 
    const decoded = extractToken(req); 
    const userid = decoded['id']; 

    try {
        const result = db.query('SELECT * FROM likes WHERE liker_id = $1 AND post_id = $2', [userid, postid]); 
        return res.status(200).json({"likedStatus" : (result !== undefined)}); 
    } catch (err) {
        res.status(500).json({"error" : err}); 
    }
});

router.post('/social/like/:postid', (req, res) => {
    const {postid} = req.params; 

    const decoded = extractToken(req); 

    const userid = decoded['id']; 
    try {
        db.query('INSERT INTO likes (liker_id, post_id) VALUES ($1, $2)', [userid, postid]); 
        return res.status(201).json({"message" : "Liked post succesfully!"}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    }
}); 

router.patch('/social/updatelike/:postid', (req, res) => {
    const {postid} = req.params; 
    try {
        db.query('UPDATE posts SET likes = likes + 1 WHERE id = $1', [postid]); 
        return res.status(204).json({"message" : "like count updated succesfully!"}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    }
});

router.delete('social/unlike/:postid', (req, res) => {
    const {postid} = req.params; 

    const decoded = extractToken(req); 

    const userid = decoded['id']; 
    try {
        db.query('DELETE FROM likes WHERE liker_id = $1 AND post_id = $2', [userid, postid]); 
        return res.status(200).json({"message" : "unliked post sucessfully!"}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    }
}); 

module.exports = router; 