# rainbow-todolist-server

The application server module. Implemented in Java & Spring version. Actually, just the initial prototype.

Provides REST API on the following adress:

    http://localhost:8000/api/v0.1/rainbowtodolist/

That's it for now.

## building
Build:

    mvn clean package

Start (trivial):

    java -jar target/rainbow-todolist-spring-*.jar

Build docker image:

    sudo docker build -t martlin2cz/rainbowtodolist-server-spring .

Start docker image:

    sudo docker run -p 8080:8080 martlin2cz/rainbowtodolist-server-spring


