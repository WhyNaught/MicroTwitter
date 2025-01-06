# remember to run "source .venv/bin/activate"
import os 
import psycopg2
from dotenv import load_dotenv
from flask import Flask, request, jsonify
from flask_bcrypt import Bcrypt
from flask_jwt_extended import JWTManager, create_access_token, jwt_required 

load_dotenv()

app = Flask(__name__)
url = os.getenv('DB_URL')
connection = psycopg2.connect(url)
bcrypt = Bcrypt(app) 

app.config["SECRET_KEY"] = os.getenv('SECRET_KEY')
app.config["JWT_SECRET_KEY"] = os.getenv('JWT_SECRET_KEY')
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
                    #return jwt, but for now just a status code 
                    return jsonify({"message" : "logged in succesfully!"}), 200
                else: 
                    return jsonify({"message" : "incorrect email or password"}), 400    
            else:
                return jsonify({"message" : "incorrect email or password"}), 400
    except Exception as e:
        return {"error": str(e)}, 500
    
# this microservice is currently running on port 5000
# "python3 app.py"
if __name__ == '__main__':
    app.run(debug=True)