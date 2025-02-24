# Microtwitter - Twitter Made With Microservices 

## Fully functional web application written in Java, JavaScript, and Python to facilitate all the features associated with Twitter, containerized with docker and orchestrated with Kubernetes

This project aims to mimic all the features of Twitter such as following, liking, commenting, retweeting, posting, and so much more. This app was built using microservices architecture, and as such consists of a variety of different services to facilitate each individual feature. These services include:
* Retweet-Service
* Social-Service
* User-Service 
* Search-Service 
* Tweet-Service 

## Quickstart

Ensure that you have the following installed:
* Minikube 
* PostgreSQL
* Git 

1. Clone the reposistory 
```sh
git clone https://github.com/WhyNaught/MicroTwitter.git
cd microtweets 
```

2. Create a new PSQL database
```sh
psql -U postgres -c "CREATE DATABASE microtweets;"
```

3. Initialize your new database 
```sh
psql -U your_username -d your_database -f init.sql
```

4. Deploy app using minikube 
```sh
cd k8s 
kubectl apply -f ./deployments/*
kubectl apply -f ./services/*
```

5. Check if pods have started 
```sh
kubectl get pods 
```

6. Access frontend with your browser 