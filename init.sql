CREATE TABLE users(
    id SERIAL NOT NULL PRIMARY KEY, 
    username varchar(50) NOT NULL UNIQUE, 
    email varchar(255) NOT NULL UNIQUE, 
    password varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL, 
    follower_count INT NOT NULL DEFAULT 0
);  

CREATE TABLE followers(
    user_id INT NOT NULL, 
    follower_id INT NOT NULL, 
    PRIMARY KEY (user_id, follower_id), 
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, 
    FOREIGN KEY (follower_id) REFERENCES users(id) ON DELETE CASCADE
); 

CREATE TABLE likes(
    liker_id INT NOT NULL, 
    post_id INT NOT NULL, 
    PRIMARY KEY (post_id, liker_id), 
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE, 
    FOREIGN KEY (liker_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE posts(
    id SERIAL NOT NULL PRIMARY KEY,
    author_id INT NOT NULL, 
    title varchar(50) NOT NULL, 
    body varchar(255),
    likes INT NOT NULL DEFAULT 0, 
    comment_count INT NOT NULL DEFAULT 0, 
    post_date varchar(255) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE comments(
    id SERIAL NOT NULL PRIMARY KEY, 
    author_id INT NOT NULL, 
    body varchar(255) NOT NULL, 
    likes INT NOT NULL DEFAULT 0, 
    comment_date varchar(255) NOT NULL, 
    FOREIGN KEY (author_id) REFERENCES users(id)
); 