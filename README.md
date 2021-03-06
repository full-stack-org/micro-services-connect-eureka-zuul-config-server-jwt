# micro-services-connect-eureka-zuul-config-server-jwt

Connect Micro Service Examples
**********************************

Ports:
-------------
connect-cloud-config-server :8888
connect-eureka-server: 8761
connect-zuul-api-gateway: 8765
connect-consumer-service: 8082
connect-producer-service:8081
connect-user-details-service: 8083

Direct End points:
-------------------------------
Eureka: http://localhost:8761/

Producer Service : http://localhost:8081/name/v1/getName

Consumer Service : http://localhost:8082/name/v1/getName

Cloud Config Server: http://localhost:8888

User Service:
---------------

Save User: http://localhost:8083/user/v1/saveUser

Find User By Email Id: http://localhost:8083/user/v1/loadByEmailId

Authenticate : http://localhost:8083/user/v1/authenticate

Requests via Zuul APi Gate Way
-------------------------------
Producer Service : http://localhost:8765/connect-producer-service/name/v1/getName

Consumer Service : http://localhost:8765/connect-consumer-service/name/v1/getName

User Service:

Save User: http://localhost:8765/connect-user-details-service/user/v1/saveUser

Find User By Email Id: http://localhost:8765/connect-user-details-service/user/v1/loadByEmailId

Authenticate :  http://localhost:8765/connect-user-details-service/user/v1/authenticate

Database details:
------------------------------

mysql> show databases;

mysql> create database connect_micro_service;

Swagger UI
------------
Producer Service : http://localhost:8081/swagger-ui.html

Consumer Service : http://localhost:8082/swagger-ui.html

User Service: http://localhost:8083/swagger-ui.html

Api Docs JSON
--------------
Producer Service : http://localhost:8081/v2/api-docs

Consumer Service : http://localhost:8082/v2/api-docsl

User Service: http://localhost:8083/v2/api-docs

Useful security tutorial:
----------------------------
https://www.xoriant.com/blog/product-engineering/microservices-security-using-jwt-authentication-gateway.html
https://medium.com/@arjunac009/spring-boot-microservice-with-centralized-authentication-zuul-eureka-jwt-5719e05fde29

https://dzone.com/articles/how-to-achieve-oauth2-security-in-microservices-di


Simple
***********
https://medium.com/@mool.smreeti/microservices-with-spring-boot-authentication-with-jwt-and-spring-security-6e10155d9db0
https://www.javainuse.com/spring/boot-jwt
