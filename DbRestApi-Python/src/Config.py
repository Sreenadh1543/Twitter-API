from flask import Flask
from flask_pymongo import PyMongo
import py_eureka_client.eureka_client as eureka_client

app = Flask(__name__)
app.config["MONGO_URI"] = "mongodb://localhost:27017/FrescoTweet"
mongo = PyMongo(app)    
