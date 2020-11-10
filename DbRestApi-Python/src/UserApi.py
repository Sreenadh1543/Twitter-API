from bson.json_util import dumps
from flask import jsonify, request
import json, re
from datetime import datetime
from flask_cors import CORS, cross_origin
from Config import mongo, app
from Models import *

cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

@app.route('/searchuser', methods=['POST'])
@cross_origin()
def search_user():
	resp = jsonify(dict())
	resp.status_code = 200
	return resp	

@app.route('/subscriber', methods=['POST'])
@cross_origin()
def subscriber():
	resp = jsonify("OK")
	resp.status_code = 200
	return resp
