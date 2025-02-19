CREATE TABLE users(
    id SERIAL NOT NULL PRIMARY KEY, 
    username varchar(50) NOT NULL UNIQUE, 
    email varchar(255) NOT NULL UNIQUE, 
    password varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL, 
    follower_count INT NOT NULL DEFAULT 0
);  

ALTER TABLE users ADD COLUMN search_vector tsvector;
UPDATE users SET search_vector = to_tsvector('english', username || ' ' || first_name || ' ' ||last_name);
CREATE INDEX users_search_vector_idx ON users USING gin(search_vector);

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

CREATE TABLE retweetLikes(
    liker_id INT NOT NULL, 
    retweet_id INT NOT NULL, 
    PRIMARY KEY (retweet_id, liker_id), 
    FOREIGN KEY (retweet_id) REFERENCES retweets(id) ON DELETE CASCADE, 
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
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE, 
);

CREATE TABLE retweets(
    id SERIAL NOT NULL PRIMARY KEY,
    author_id INT NOT NULL,
    post_id INT NOT NULL, 
    title varchar(50) NOT NULL, 
    body varchar(255), 
    retweeter_id INT NOT NULL, 
    likes INT NOT NULL DEFAULT 0, 
    comment_count INT NOT NULL DEFAULT 0, 
    post_date varchar(255) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (retweeter_id) REFERENCES users(id) ON DELETE CASCADE, 
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE 
);

ALTER TABLE posts ADD COLUMN search_vector tsvector;
UPDATE posts SET search_vector = to_tsvector('english', title || ' ' || body);
CREATE INDEX posts_search_vector_idx ON posts USING gin(search_vector);

CREATE TABLE comments(
    id SERIAL NOT NULL PRIMARY KEY, 
    author_id INT NOT NULL, 
    body varchar(255) NOT NULL, 
    likes INT NOT NULL DEFAULT 0, 
    comment_date varchar(255) NOT NULL, 
    FOREIGN KEY (author_id) REFERENCES users(id)
); 