from flask import Flask, request

app = Flask(__name__)

@app.route('/api/check')
def check():
    if "uuid" not in request.args.keys():
        return "Bad Request", 400
    
    # 200 - whitelist
    # 401 - not whitelist
    # 403 - banned

    return "Arbitrary Content", 403
