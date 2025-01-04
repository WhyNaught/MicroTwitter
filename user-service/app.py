from flask import Flask

app = Flask(__name__)

@app.route('/', methods = ['GET'])
def home():
    return "Test"

# this microservice is currently running on port 5000
if __name__ == '__main__':
    app.run(debug=True)