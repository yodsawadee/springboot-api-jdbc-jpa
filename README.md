# springboot-api-jdbc-jpa
springboot-api-jdbc-jpa with postgres database connection

## step run project (Local)
1. create database in postgres
- Start the DB: `docker-compose -f docker/docker-compose.db.yml up -d`
- Stop everything: `docker-compose down`
2. start kafka
- `docker-compose -f docker/docker-compose.kafka.yml up -d`

## View documentation
```
http://localhost:8082/swagger-ui/index.html
```

## วิธี build image
```
docker compose build
```
ให้ docker-compose build image จาก Dockerfile ใน folder ปัจจุบัน (.)

## วิธี run container
1. สร้าง network เพื่อให้ container ต่างๆ สามารถสื่อสารกันได้
```
docker network create mynetwork
```
2. รัน container ของ postgres
```
docker run -d \
--name postgres \
--network mynetwork \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=testDb \
-p 5432:5432 \
postgres:16-alpine
```
และ springboot app
```
docker run -d \
--name springboot-app \
--network mynetwork \
-p 8082:8082 \
-e SPRING_PROFILES_ACTIVE=docker \
jan/springboot-api:latest
```

## วิธี build image และ run container ด้วย docker compose
```
docker compose up
```