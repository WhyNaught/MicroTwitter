const express = require("express");
const { createProxyMiddleware } = require("http-proxy-middleware");
require("dotenv").config();

const app = express();
const PORT = process.env.DEV_PORT || 117;

app.use((req, res, next) => {
    console.log(`[Gateway] ${req.method} ${req.originalUrl}`);
    next();
});

const services = {
    "/users" : "http://user-service:5000", // user-service 
    "/tweets" : "http://tweet-service:5001", // tweet-service
    "/social" : "http://social-service:5002", // social-service
    "/search" : "http://search-service:5003", // search-service
    "/retweets" : "http://retweet-service:8080" // retweet-service
};

Object.keys(services).forEach((path) => {
    app.use(
        path,
        createProxyMiddleware({
            target: services[path],
            changeOrigin: true,
        })
    );
  });
  
app.get("/", (req, res) => {
    res.send("API Gateway is running");
});
  
app.listen('0.0.0.0', PORT, () => {
    console.log(`API Gateway running on port ${PORT}`);
});