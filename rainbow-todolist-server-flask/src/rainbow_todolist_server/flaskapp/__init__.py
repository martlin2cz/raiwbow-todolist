from flask import Flask

from ..logic import applogic


def create_app():
    app = Flask(__name__)

    @app.route("/api/v0.2/rainbowtodolist")
    def index():
        return "Welcome to rainbow todolist!"

    @app.route("/api/v0.2/rainbowtodolist/tasks")
    def list_tasks():
        return applogic.list_tasks()

    return app

