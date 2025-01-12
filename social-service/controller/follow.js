const express = require('express'); 
const router = express.Router(); 
const db = require('../db');
const extractToken = require('./extractToken');  
require('dotenv').config(); 

router.get('/social/checkfollow/:userid', async (req, res) => {
    const {userid} = req.params; 
    const decoded = extractToken(req); 
    const followerid = decoded['id']; 
    try {
        const result = await db.query('SELECT * FROM followers WHERE user_id = $1 AND follower_id = $2', [userid, followerid]); 
        return res.status(200).json({"followStatus" : (result.rowCount > 0)}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    }
}); 

router.post('/social/follow/:userid', async (req, res, next) => {
    const {userid} = req.params;
    const decoded = extractToken(req);
    const followerid = decoded['id']; 
    try {
        await db.query('INSERT INTO followers (user_id, follower_id) VALUES ($1, $2)', [userid, followerid]);
        return res.status(201).json({"message" : "Followed succesfully!"}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    } finally {
        next(); 
    }
});

router.delete('/social/unfollow/:userid', async (req, res, next) => {
    const {userid} = req.params;
    
    const decoded = extractToken(req); 

    try {
        const followerid = decoded['id']; 
        await db.query('DELETE FROM followers WHERE follower_id = $1 AND user_id = $2', [followerid, userid]); 
        return res.status(201).json({"message" : "Unfollowed sucessfully!"}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    } finally {
        next(); 
    }
}); 

module.exports = router; 