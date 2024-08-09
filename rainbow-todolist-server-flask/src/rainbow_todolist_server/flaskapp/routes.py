from flask import request
from flask import Blueprint

from ..logic import applogic

bp = Blueprint('routes', __name__)

@bp.route("/api/v0.2/rainbowtodolist/tasks")
def list_tasks():
    return applogic.list_tasks()


@bp.put("/api/v0.2/rainbowtodolist/tasks")
def add_task():
    text = request.form["text"]
    return [applogic.add_task(text)]


@bp.get("/api/v0.2/rainbowtodolist/tasks/<uuid:task_id>")
def get_task(task_id):
    return [applogic.get_task(task_id)]


@bp.patch("/api/v0.2/rainbowtodolist/tasks/<uuid:task_id>")
def update_task(task_id):
    text = request.form["text"]
    return [applogic.update_task(task_id, text)]


@bp.delete("/api/v0.2/rainbowtodolist/tasks/<uuid:task_id>")
def remove_task(task_id):
    return [applogic.remove_task(task_id)]
