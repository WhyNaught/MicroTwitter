const express = require('express'); 
const router = express.Router(); 
const jwt = require('jsonwebtoken'); 
const db = require('../db'); 
require('dotenv').config(); 

router.post('/social/follow/:userid', (req, res, next) => {
    const {userid} = req.params;
    const authheader = req.headers['authorization']; 
    const token = authheader.split(' ')[1];

    if (!token) {
        return res.status(401).json({"error" : "unauthorized"}); 
    };

    try {
        const decoded = jwt.verify(token, process.env.SECRET_KEY); 
        const followerid = decoded['id']; 
        db.query('INSERT INTO followers (user_id, follower_id) VALUES ($1, $2)', [userid, followerid]);
        return res.status(201).json({"message" : "Followed succesfully!"}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    } finally {
        next(); 
    }
});

router.delete('social/unfollow/:userid', (req, res, next) => {
    const {userid} = req.params;
    const authheader = req.headers['authorization']; 
    const token = authheader.split(' ')[1];

    if (!token) {
        return res.status(401).json({"error" : "unauthorized"});
    };

    try {
        const decoded = jwt.verify(token, process.env.SECRET_KEY); 
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