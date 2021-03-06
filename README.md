# Sample Spring Boot REST Service

### Description

A sample CRUD REST service using Spring Boot, JPA, Groovy, and H2 in-memory database. Illustrates use of JPA, 
appropriate use of HTTP verbs, etc.

### Notable Features:
* Integration tests using REST Assured.
* Auto-generated UUIDs rather than longs for IDs.
* Java 8 Optional handling with Groovy.
* Swagger Documentation
* Spring Boot Actuator Metrics

### Project Setup - IntelliJ
* File > New > Project From Existing Sources
* Select Project Directory
* Import Project From External Module > Gradle
* Specify GRADLE_HOME and Java 8 JDK

### Running Locally
* Clone repository
* Set up a run configuration to execute Gradle tasks: clean bootRun
* Default server port is 8080

### Swagger Documentation
http://localhost:8080/swagger-ui.html

### Metrics
http://localhost:8080/metrics

Normally full authentication would be required to access metrics, but I've marked the endpoint as non-sensitive 
for demonstration purposes.