const express = require('express'); 
const router = express.Router(); 
const db = require('../db'); 

router.get('/search/posts', async (req, res) => {
    const {query} = req.query;

    try {
        const result = await db.query("SELECT * FROM posts WHERE search_vector @@ to_tsquery('english', $1)", [query]); 
        return res.status(200).json({"results" : result.rows});  
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    }
}); 

router.get('/search/users', async (req, res) => {
    const {query} = req.query; 

    try {
        const result = await db.query("SELECT * FROM users WHERE search_vector @@ to_tsquery('english', $1)", [query]); 
        return res.status(200).json({"result" : result.rows}); 
    } catch (err) {
        return res.status(500).json({"error" : err}); 
    }
});

module.exports = router;  