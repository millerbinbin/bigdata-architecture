docker run --name bigdata_mongo -p 27017:27017 -d mongo
docker exec -it bigdata_mongo mongo test
db.createUser({user:'mongo_user',pwd:'mongo_user',roles:[{role:"readWrite",db:"test"}]});


db.createUser({ user: 'mongo_user', pwd: 'mongo_user', roles: [ { role: "userAdminAnyDatabase", db: "admin" } ] });

docker run --name bigdata_redis -p 6379:6379 -d redis --appendonly yes