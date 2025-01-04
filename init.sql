CREATE DATABASE IF NOT EXISTS microtweets; 
CREATE TABLE users(
    id SERIAL NOT NULL PRIMARY KEY, 
    username varchar(50) NOT NULL UNIQUE, 
    email varchar(50) NOT NULL UNIQUE, 
    password varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL, 
);  

CREATE TABLE posts(
    id SERIAL NOT NULL PRIMARY KEY,
    author_id INT NOT NULL 
    title varchar(50), 
    body varchar(255),
    likes INT NOT NULL DEFAULT 0, 
    comment_count INT NOT NULL DEFAULT 0, 
    post_date varchar(255) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(id)
);

CREATE TABLE comments(
    id SERIAL NOT NULL PRIMARY KEY, 
    author_id INT NOT NULL, 
    body varchar(255) NOT NULL, 
    likes INT NOT NULL DEFAULT 0, 
    comment_date varchar(255) NOT NULL, 
    FOREIGN KEY (author_id) REFERENCES users(id)
); 