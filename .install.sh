cd .EurekaServer
setsid mvn spring-boot:run > /dev/null &
wget -qO - https://www.mongodb.org/static/pgp/server-4.2.asc | sudo apt-key add -
echo "deb [ arch=amd64 ] https://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/4.2 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.2.list
sudo apt-get update
sudo apt-get install -y mongodb-org
sudo apt install -y mongodb-server
sudo service mongodb start
pip3 install flask
pip3 install Flask-PyMongo
pip3 install py-eureka-client
pip3 install flask_cors
pip3 install mongoengine
echo "done................!"
