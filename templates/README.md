Overview
--------

Everything under the maven-layered directory will be included in the output.

TODO
====

 1. Update to use [REST spring classes](https://itnext.io/building-microservices-with-spring-data-rest-40bb94080a9e).
 2. [Swagger](https://swagger.io/) for docs... Eureka for service discovery?
 3. Create common REST interface to extend with important CRUD services.
 4. Add Hystrix to fallback gracefully when underload
 5. Add UI part to test-integration in order to run whatever tests you want.
 6. Configuration
    * Zip results from rest server.
 7. Dashboard UI
    * [load balancing, etc.](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-netflix.html)
    * Health


Complete
========

 1. [Spring data REST project](https://github.com/spring-projects/spring-data-rest)
 2. [REST docs](https://projects.spring.io/spring-data-rest/)
 3. Api side to automatically take rest results and create model objects.




References
==========

 1. [Integrate Feign and HATEOAS](https://reflectoring.io/accessing-spring-data-rest-with-feign/)
 2. [Feign/HATEOAS Working example](https://github.com/jtdev/spring-feign-data-rest-example) - Simple and works.
 3. [Feign docs](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html)
