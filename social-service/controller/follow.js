const express = require('express'); 
const router = express.Router(); 
const jwt = require('jsonwebtoken'); 
const db = require('../db');
const extractToken = require('./extractToken');  
require('dotenv').config(); 

router.post('/social/follow/:userid', (req, res, next) => {
    const {userid} = req.params;
    const decoded = extractToken(req);
    const followerid = decoded['id']; 
    try {
        db.query('INSERT INTO followers (user_id, follower_id) VALUES ($1, $2)', [userid, followerid]);
        return res.status(201).json({"message" : "Followed succesfully!"}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    } finally {
        next(); 
    }
});

router.delete('/social/unfollow/:userid', (req, res, next) => {
    const {userid} = req.params;
    
    const decoded = extractToken(req); 

    try {
        const followerid = decoded['id']; 
        db.query('DELETE FROM followers WHERE follower_id = $1 AND user_id = $2', [followerid, userid]); 
        return res.status(201).json({"message" : "Unfollowed sucessfully!"}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    } finally {
        next(); 
    }
}); 

module.exports = router; 