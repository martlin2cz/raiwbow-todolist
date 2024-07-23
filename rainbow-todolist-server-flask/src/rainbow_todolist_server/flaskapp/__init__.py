from flask import Flask
from flask import request

from ..logic import applogic


def create_app():
    app = Flask(__name__)

    @app.route("/api/v0.2/rainbowtodolist")
    def index():
        return "Welcome to rainbow todolist!"

    @app.route("/api/v0.2/rainbowtodolist/tasks")
    def list_tasks():
        return applogic.list_tasks()

    @app.put("/api/v0.2/rainbowtodolist/tasks")
    def add_task():
        text = request.form["text"]
        return [ applogic.add_task(text) ]

    @app.route("/api/v0.2/rainbowtodolist/tasks/<uuid:task_id>", methods=['GET', 'PATCH', 'DELETE'])
    def get_or_modify_task(task_id):
        if request.method == 'GET':
            return get_task(task_id)
        if request.method == 'PATCH':
            return update_task(task_id)
        if request.method == 'DELETE':
            return remove_task(task_id)

    @app.get("/api/v0.2/rainbowtodolist/tasks/get/<uuid:task_id>")
    def get_task(task_id):
        return [ applogic.get_task(task_id) ]

    @app.patch("/api/v0.2/rainbowtodolist/tasks/update/<uuid:task_id>")
    def update_task(task_id):
        text = request.form["text"]
        return [ applogic.update_task(task_id, text) ]

    @app.delete("/api/v0.2/rainbowtodolist/tasks/remove/<uuid:task_id>")
    def remove_task(task_id):
        return [ applogic.remove_task(task_id) ]

    return app

