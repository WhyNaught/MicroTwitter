const express = require('express');
const expressJwt = require('express-jwt').expressjwt;  
const cors = require('cors'); 
require('dotenv').config(); 

const app = express(); 

app.use(cors()); 

const port = process.env.DEV_PORT; 
app.listen(port, 'localhost', () => {
    console.log('API gateway started on port ' + port); 
});