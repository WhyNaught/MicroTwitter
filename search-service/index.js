const express = require('express'); 
const app = express(); 
const cors = require('cors'); 
const search = require('./controller/search'); 
require('dotenv').config(); 

app.use(cors()); 

const port = 5003; 

app.listen(port, () => {
    console.log('Search service started on port 5003'); 
}); 

app.use(search); 