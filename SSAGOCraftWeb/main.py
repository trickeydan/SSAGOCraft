from flask import Flask, request

app = Flask(__name__)

@app.route('/api/check')
def check():
    if "uuid" not in request.args.keys():
        return "Bad Request", 400
    
    if True:
        return "User on whitelist", 200
    else:
        return "User not on whitelist", 401
