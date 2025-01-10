const {Pool} = require('pg'); 
require('dotenv').config()

const pool = new Pool({
    username: "postgres", 
    password: process.env.DB_PWD, 
    host: 'localhost', 
    port: 5432, 
    database: 'microtweets'
}); 

module.exports = {
    query: (text, params) => {pool.query(text, params)} 
}; 