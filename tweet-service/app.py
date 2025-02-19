import os 
from flask import Flask, request, jsonify
from dotenv import load_dotenv
import psycopg2
import datetime
import jwt as pyjwt 
from functools import wraps 

app = Flask(__name__)
load_dotenv()
db_url = os.getenv('DB_URL')
secret_key = os.getenv('SECRET_KEY')
connection = psycopg2.connect(db_url)

# custom implementation of flask-jwt-extended's token verification method using decorators
def token_required(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        token = None
        if 'Authorization' in request.headers:
            auth_header = request.headers['Authorization']
            parts = auth_header.split(' ')
            if len(parts) == 2 and parts[0] == 'Bearer':
                token = parts[1]
        if not token:
            return jsonify({"error": "No token found"}), 401
        try:
            data = pyjwt.decode(token, secret_key, algorithms=["HS256"])
            request.user = data
        except pyjwt.ExpiredSignatureError:
            return jsonify({"error": "Token has expired"}), 401
        except pyjwt.InvalidTokenError:
            return jsonify({"error": "Token is invalid"}), 401
        return f(*args, **kwargs)
    return decorated_function

@app.route('/tweet/post/<id>', methods = ['POST'])
@token_required
def post_tweet(id):
    data = request.get_json()
    title = data['title']
    body = data['body']
    post_date = datetime.datetime.now()
    try:
        with connection: 
            with connection.cursor() as cursor: 
                cursor.execute('INSERT INTO posts (author_id, title, body, post_date) VALUES (%s, %s, %s, %s)', (id, title, body, post_date))
        return {'message' : 'tweeted succesfully!'}, 201
    except Exception as e: 
        return {'error' : str(e)}, 500
    
@app.route('/tweet/getall/<id>', methods = ['GET'])
def get_all(id):
    try:
        with connection:
            with connection.cursor() as cursor:
                cursor.execute('SELECT * FROM posts WHERE author_id = %s', (id))
                posts = cursor.fetchall()
                if not posts:
                    return {"message" : "no posts from this user yet"}, 404
                return jsonify({"message" : "posts retrieved successfully!", "posts" : posts}), 200 
    except Exception as e:      
        return {"error" : str(e)}, 500
    
@app.route('/tweet/getone/<tweetid>', methods = ['GET'])
def get_one(tweetid):
    try:
        with connection:
            with connection.cursor() as cursor:
                cursor.execute('SELECT * FROM posts WHERE id = %s', (tweetid))
                post = cursor.fetchone()
                if not post: 
                    return jsonify({"message" : "no such post with this id exists"}), 404
                return jsonify({"post" : post}), 200
    except Exception as e:
        return {"error" : str(e)}, 500

@app.route('/tweet/deleteone/<tweetid>', methods = ['DELETE'])
@token_required
def deleteone(tweetid):
    try:
        user_id = request.user['user_id']
        with connection:
            with connection.cursor() as cursor:
                cursor.execute('SELECT author_id FROM posts WHERE id = %s', (tweetid))
                post = cursor.fetchone()
                if not post:
                    return jsonify({"error" : "no post with this id was found"}), 404
                author_id = post[1]

                if author_id != user_id:
                    return jsonify({"error" : "not authorized"}), 401
                
                cursor.execute('DELETE FROM posts WHERE id = %s', (tweetid))
                return jsonify({"message" : "post deleted succesfully!"}), 200
    except Exception as e:
        return {"error" : str(e)}, 500

# no need for a put/patch route as twitter does not allow editing of posts
    
if __name__ == '__main__':
    app.run('0,0.0.0', debug=True, port=5001)