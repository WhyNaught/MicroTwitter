const express = require('express');
const jwt = require('jwt'); 
const cors = require('cors'); 
require('dotenv').config(); 

const app = express(); 

const user_service_url = ''; 
const search_service_url = ''; 
const tweet_service_url = ''; 
const comment_service_url = ''; 
const reply_service_url = ''; 

app.use(cors()); 

const port = process.env.DEV_PORT; 
app.listen(port, 'localhost', () => {
    console.log('API gateway started on port ' + port); 
});