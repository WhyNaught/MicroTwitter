# remember to run "source .venv/bin/activate"
import os 
import psycopg2
from dotenv import load_dotenv
from flask import Flask, request
from flask_bcrypt import Bcrypt

load_dotenv()

app = Flask(__name__)
url = os.getenv('DB_URL')
connection = psycopg2.connect(url)
bcrypt = Bcrypt(app) 

register_query = """INSERT INTO users (username, email, password, first_name, last_name) VALUES (%s, %s, %s, %s, %s)"""
@app.route('/register', methods = ['POST'])
def register():
    try:
        data = request.get_json()
        username = data["username"]
        email = data["email"]
        password = data["password"]
        hashed_password = bcrypt.generate_password_hash(password).decode('utf-8')
        first_name = data["first_name"]
        last_name = data["last_name"]
        with connection:
            with connection.cursor() as cursor:
                cursor.execute(register_query, (username, email, hashed_password, first_name, last_name))
        return {"message": "user registered successfully"}, 201
    except Exception as e:
        return {"error": str(e)}, 500

@app.route('/login', methods = ['GET'])
def login():
    try:
        data = request.get_json()
        username = data["username"]
        password_guess = data["password"]
    except Exception as e:
        return {"error": str(e)}, 500
    
# this microservice is currently running on port 5000
# "python3 app.py"
if __name__ == '__main__':
    app.run(debug=True)