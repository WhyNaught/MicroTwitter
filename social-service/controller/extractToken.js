const jwt = require('jsonwebtoken'); 
require('dotenv').config(); 

function extractToken(req) {
    const authheader = req.headers['authorization']; 
    const token = authheader.split(' ')[1];

    if (!token) {
        return res.status(401).json({"error" : "unauthorized"}); // consider just returning false and handling logic in controller 
    };

    const decoded = jwt.verify(token, process.env.SECRET_KEY);
    return decoded; 
}; 

module.exports = extractToken; 