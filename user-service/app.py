# remember to run "source .venv/bin/activate"
import os 
import psycopg2
from dotenv import load_dotenv
from flask import Flask, request, jsonify
from flask_bcrypt import Bcrypt
import jwt as pyjwt 
import datetime

load_dotenv()

app = Flask(__name__)
url = os.getenv('DB_URL')
connection = psycopg2.connect(url)
bcrypt = Bcrypt(app) 

secret_key = os.getenv('SECRET_KEY')
app.config["JWT_TOKEN_LOCATION"] = ['headers']

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
                register_query = '''INSERT INTO users (username, email, password, first_name, last_name) VALUES (%s, %s, %s, %s, %s)'''
                cursor.execute(register_query, (username, email, hashed_password, first_name, last_name))
        return {"message": "user registered successfully"}, 201
    except Exception as e:
        return {"error": str(e)}, 500

@app.route('/login', methods = ['GET'])
def login():
    try:
        data = request.get_json()
        email = data["email"]
        password_guess = data["password"]
        with connection:
            with connection.cursor() as cursor:
                cursor.execute('''SELECT * FROM users WHERE email = %s''', (email, ))
                user = cursor.fetchone()
            if user:
                if bcrypt.check_password_hash(user[3], password_guess):
                    payload = {
                        "email" : email,
                        "id" : user[0], 
                        "exp" : datetime.datetime.utcnow() + datetime.timedelta(hours=1)
                    }
                    token = pyjwt.encode(payload, secret_key)
                    return jsonify({"message" : "logged in succesfully!", "token" : token}), 200
                else: 
                    return jsonify({"message" : "incorrect email or password"}), 400    
            else:
                return jsonify({"message" : "incorrect email or password"}), 400
    except Exception as e:
        return {"error": str(e)}, 500
    
# this microservice is currently running on port 5000
if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)