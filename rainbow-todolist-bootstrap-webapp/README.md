# rainbow-todolist-bootstrap-webapp

The simple webapp (frontend for the server), based on bootstrap. More info later.

# Simple run
Open index.html in the browser. PRobably won't work due to the CORS policy violation.

# Docker run
Build and run on local nginx server by docker, like so:

    $ docker build -t rainbow-todolist-bootstrap-webapp .
    $ docker run -p 8080:80 -t rainbow-todolist-bootstrap-webapp


