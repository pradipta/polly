docker network create polly-bridge
docker build -f Dockerfile -t pollybackend .

docker container run --name mysqlindocker --network polly-bridge -e MYSQL_ROOT_PASSWORD=ueducation -e MYSQL_DATABASE=polly -d mysql:8

docker create --network polly-bridge --name pollybackendcontainer -p 8080:8080 pollybackend

docker start pollybackendcontainer