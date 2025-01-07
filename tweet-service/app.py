import os 
from flask import Flask, request, jsonify
from dotenv import load_dotenv
import psycopg2
import datetime

app = Flask(__name__)
load_dotenv()
db_url = os.getenv('DB_URL')
connection = psycopg2.connect(db_url)

@app.route('/tweet/post/<id>', methods = ['POST'])
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
    
@app.route('/tweet/getall/<id>')
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
    
if __name__ == '__main__':
    app.run(debug=True)