import string, random
try:
    import pymongo, json, requests
    from bson.json_util import dumps
except:
    pass

FS_SCORE = 0
myclient = ""
try:
	myclient = pymongo.MongoClient("mongodb://localhost:27017/")
	mydb = myclient["FrescoTweet"]
	mycol = mydb["userposts"]
except:
	pass
def randomString(stringLength=20):
    """Generate a random string of fixed length """
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(stringLength))

POST_BODY = randomString()

try:
	url = 'http://localhost:8761'
	x = requests.get(url)
	if 'DBRESTAPI' in x.text:
		FS_SCORE += 10
except:
	pass
try:
    mycol.remove({})
except:
    pass
url = 'http://localhost:5000/addpost'
myobj = {'user': 'user@gmail.com', 'postBody': POST_BODY
}
try:
	x = requests.post(url, data = myobj)
	data = json.loads(dumps(mycol.find({})))
	if len(data) > 0:
		data = data[0]
		if data['_id'] == 'user@gmail.com':
			FS_SCORE += 5
		data = data['posts']
		if len(data) > 0 :
			data = data[0]
			if data['postBody'] == POST_BODY :
				FS_SCORE += 10
			if data['postDate'] != None or data['postDate'] != "" :
				FS_SCORE += 5
except:
	pass

url = 'http://localhost:5000/getposts'
myobj = {'user': 'user@gmail.com'}
try:
	x = requests.post(url, data = myobj)

	if x.status_code == 200:
		data = x.json()
		if len(data) > 0:
			data = data[0]
			if data['postBody'] == POST_BODY :
				FS_SCORE += 20
except:
	pass

try:
    url = 'http://localhost:5000/delpost'
    myobj = {'user': 'user@gmail.com', 'postId': data['postId']}
    x = requests.post(url, data = myobj)
    data = json.loads(dumps(mycol.find({})))
    if len(data[0]['posts']) == 0:
        FS_SCORE += 20
except:
	pass
try:
    mycol.remove({})
    mycol.insert({ "_id" : "user@gmail.com", "posts" : [ ], "subscribed" : ['user2@gmail.com', 'user4@gmail.com'] })
    mycol.insert({ "_id" : "user1@gmail.com", "posts" : [ ], "subscribed" : [ ] })
    mycol.insert({ "_id" : "user2@gmail.com", "posts" : [ ], "subscribed" : [ ] })
    mycol.insert({ "_id" : "user3@gmail.com", "posts" : [ ], "subscribed" : [ ] })
    mycol.insert({ "_id" : "user4@gmail.com", "posts" : [ ], "subscribed" : [ ] })
    mycol.insert({ "_id" : "user5@gmail.com", "posts" : [ ], "subscribed" : [ ] })
except:
    pass
url = 'http://localhost:5000/searchuser'
myobj = {'user': 'user@gmail.com', 'searchText': 'user'}
try:
	x = requests.post(url, data = myobj)
	if x.status_code == 200:
		data = x.json()
		for i in range(1,6) :
			if i % 2 == 0 :
				if data['user'+str(i)+'@gmail.com']:
					FS_SCORE += 2
			else:
				if not data['user'+str(i)+'@gmail.com']:
					FS_SCORE += 2
except:
	pass

try:
	url = 'http://localhost:5000/subscriber'
	myobj = {'user': 'user@gmail.com', 'subuser': 'user2@gmail.com'}
	x = requests.post(url, data = myobj)
	
	url = 'http://localhost:5000/subscriber'
	myobj = {'user': 'user@gmail.com', 'subuser': 'user5@gmail.com'}
	x = requests.post(url, data = myobj)
	data = json.loads(dumps(mycol.find({'_id': 'user@gmail.com'},{'subscribed':1,'_id':0})))
	data = data[0]['subscribed']
	if len(data) == 2:
		FS_SCORE += 10
	if 'user4@gmail.com' in data :
		FS_SCORE += 5
	if 'user5@gmail.com' in data :
		FS_SCORE += 5
except:
	pass

print("FS_SCORE:" + str(FS_SCORE) + "%")
