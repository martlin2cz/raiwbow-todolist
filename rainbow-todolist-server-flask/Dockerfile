# syntax=docker/dockerfile:1
FROM python:3.8-slim-buster

WORKDIR /python-docker

COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt

COPY src .

CMD [ "python3.8", "-m" , "flask", "--app", "rainbow_todolist_server/flaskapp/", "run", "--host=0.0.0.0"]