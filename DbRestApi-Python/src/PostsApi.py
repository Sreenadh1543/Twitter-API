from bson.json_util import dumps
from flask import jsonify, request
import json, re
from datetime import datetime
from flask_cors import CORS, cross_origin
from Config import mongo, app
from Models import *

cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

@app.route('/addpost', methods=['POST'])
@cross_origin()
def add_post():
    resp = jsonify('OK')
    resp.status_code = 200
    return resp

@app.route('/getposts', methods=['POST'])
@cross_origin()
def get_posts():
    resp = jsonify(list())
    resp.status_code = 200
    return resp

@app.route('/delpost', methods=['POST'])
@cross_origin()
def del_posts():
    resp = jsonify('OK')
    resp.status_code = 200
    return resp

