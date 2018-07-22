Overview
========

This is the service.  It's the application that starts up and exposes the services via REST controllers.

Starting the Application
========================

Since this is a Spring Boot application startup is easy.

Build the project in the project's root directory using Maven.

```bash
cd <your-project>
mvn clean install
```

Once it builds, the service application will be located in the target directory of the service sub-project.

Go into the target directory and start the application.

```bash
cd <your-project>-service/target
java -jar <your-project>-service-0.0.1-SNAPSHOT.jar
```


Actuator Endpoints
==================


All Spring Actuator endpoints are turned on by default.  They can be disabled by changing the corresponding property.


### application.properties

```bash
management.security.enabled=true
```

You can access the endpoints via curl.

```bash
curl -s localhost:8080/health
```

```json
{"status":"UP","diskSpace":{"status":"UP","total":498753077248,"free":244306657280,"threshold":10485760},"db":{"status":"UP","database":"H2","hello":1}}
```

If you have jq installed, the result can be formatted:

```bash
curl -s localhost:8080/health | jq .
```

```json
{
  "status": "UP",
  "diskSpace": {
    "status": "UP",
    "total": 498753077248,
    "free": 244307755008,
    "threshold": 10485760
  },
  "db": {
    "status": "UP",
    "database": "H2",
    "hello": 1
  }
}
```

The full list of endpoints can be found by:

```bash
curl -s localhost:8080/mappings
```

or

```bash
curl -s localhost:8080/mappings | jq .
```

Documentation is available at [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-endpoints-exposing-endpoints)


REST Controllers
================

You can test the controllers with [HTTPie](https://httpie.org/doc) or any other REST testing tool you wish: cURL, wget, etc.

```bash
http :8080/appUser name=='my new user'

HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Date: Sun, 22 Jul 2018 03:02:40 GMT
Transfer-Encoding: chunked
X-Application-Context: application

{
    "content": "my new user",
    "id": 11
}
```

Database
========

The default database is H2.  This is an in memory database and the data doesn't persist across application restarts.

You can update the database by changing the driver in the <your-project>-service/pom.xml.

Remove the h2 and replace with whatever is appropriate; MySQL, Postgres, etc.

```xml
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
```

Once the application is started, you can access the H2 in memory database via URL.

```
http://localhost:8080/h2-console
```

Login: sa
Password: sa