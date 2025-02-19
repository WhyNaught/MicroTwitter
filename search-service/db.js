const {Pool} = require('pg'); 
require('dotenv').config()

const pool = new Pool({
    username: "postgres", 
    password: process.env.DB_PWD, 
    host: 'postgresql-service', 
    port: 5432, 
    database: 'microtweets'
}); 

module.exports = {
    query: (text, params) => {return pool.query(text, params)} 
}; 