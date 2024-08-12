# rainbow-todolist-server-flask

The oficial server, implemented in Python by using the flask framework. Provides API on `http://localhost:5000/api/v0.2/rainbowtodolist/`.

## Running
### Run directly

    python3.8 -m venv .venv
    . .venv/bin/activate
    flask --app src/rainbow_todolist_server/flaskapp/ run

### Run by docker

    docker build --tag=rainbow-todolist-flask-server
    docker run -p 5000:5000 rainbow-todolist-flask-server

    

