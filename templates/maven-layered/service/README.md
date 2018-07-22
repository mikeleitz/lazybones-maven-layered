Overview
========

This is the service.  It's the application that starts up and exposes the services via REST controllers.

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