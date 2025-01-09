const express = require('express'); 
const cors = require('cors'); 
const app = express(); 
const follow = require('./controller/follow'); 
const like = require('./controller/like'); 
require('dotenv').config(); 

app.use(cors()); 

const port = 5002; 
app.listen(port, () => {
    console.log("Social Graph started on port " + port); 
});

app.use(follow); 
app.use(like); 