CREATE TABLE users(
    id SERIAL NOT NULL PRIMARY KEY, 
    username varchar(50) NOT NULL, 
    email varchar(50) NOT NULL, 
    password varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL
);  

CREATE TABLE posts(
    id SERIAL NOT NULL PRIMARY KEY,
    body varchar(255),  
    author varchar(255) NOT NULL, 
    post_date varchar(255) NOT NULL
);

CREATE TABLE comments(
    id SERIAL NOT NULL PRIMARY KEY, 
    author varchar(255) NOT NULL, -- change this maybe
    comment_date varchar(255) NOT NULL, 
)