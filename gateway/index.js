const express = require("express");
const { createProxyMiddleware } = require("http-proxy-middleware");
require("dotenv").config();

const app = express();
const PORT = process.env.DEV_PORT;

app.use((req, res, next) => {
    console.log(`[Gateway] ${req.method} ${req.originalUrl}`);
    next();
});

const services = {
    "/users" : "http://localhost:5000", // user-service 
    "/tweets" : "http://localhost:5001", // tweet-service
    "/social" : "http://localhost:5002", // social-service
    "/search" : "http://localhost:5003", // search-service
    "/retweets" : "http://localhost:8080" // retweet-service
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
  
app.listen(PORT, () => {
    console.log(`API Gateway running on http://localhost:${PORT}`);
});