# Custom Flowable BPM Spring Boot App + Apps

So you need some of Flowable UI apps but also need to build your own Flowable Spring Boot application?  This a project that can help guide you.

## What is This?

This project is composed of a `docker-compose.yml`, a spring boot application exposing the REST API with security enabled, and `Dockerfile`s for each of the Flowable management apps (Admin, IDM, and Modeler).

## How to Use It

Make any changes you need to `flowable-boot-app` and then run a `mvn clean install` from the root of this project.  This will compile the boot app and execute a `docker-compose up` to get everything running at the following addresses:
- flowable-boot-app: http://localhost:18080/
- flowable-idm: http://localhost:18888/
- flowable-admin: http://localhost:19988/
- flowable-modeler: http://localhost:18088/
- MySQL database: localhost:13306

Like all Flowable examples, this project uses the username `admin` and the password `test` for rest and UI users.  It also uses `flowable` for both the username and password on the database.