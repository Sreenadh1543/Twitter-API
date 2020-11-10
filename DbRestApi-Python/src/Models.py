from mongoengine import *

connect('FrescoTweet')

class post(EmbeddedDocument):
    postId = IntField(required=True)
    postBody = StringField()
    postDate = DateTimeField()

class userposts(Document):
    _id = StringField()
    posts = ListField(EmbeddedDocumentField(Post))
    subscribed = ListField()


