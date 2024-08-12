from dataclasses import dataclass
from typing import Any, Dict

from flask import request
from flask import Blueprint

from ..logic import applogic

bp = Blueprint('routes', __name__)


@dataclass(init=False)
class RainbowTasksResponse:
    """
    The simple entity which just encapsulates the actual response data and provides a bit of a further context.
    Attributes:
        explanation: the human-readable explanation of the action performed or the result data
        result: the actual data
        count: number of the result elements
    """
    explanation: str
    result: Any
    count: int

    def __init__(self, explanation: str, result: Any):
        self.explanation = explanation
        self.result = result
        self.count = len(result) if isinstance(result, list) else 1

    def serialize(self) -> Dict[str, Any]:
        """ Serialises this into the dict. """
        return self.__dict__


@bp.route("/api/v0.2/rainbowtodolist/tasks")
def list_tasks():
    """ Lists all the tasks. """
    listed_tasks = applogic.list_tasks()
    return RainbowTasksResponse("All tasks", listed_tasks).serialize()


@bp.put("/api/v0.2/rainbowtodolist/tasks")
def add_task():
    """ Adds the task with properties sent by the form. Returns that new task. """
    text = request.form["text"]
    added_task = applogic.add_task(text)
    return RainbowTasksResponse("Task added", added_task).serialize()


@bp.get("/api/v0.2/rainbowtodolist/tasks/<uuid:task_id>")
def get_task(task_id):
    """ Returns the task of the specified id. """
    got_task = applogic.get_task(task_id)
    return RainbowTasksResponse("Got task", got_task).serialize()


@bp.patch("/api/v0.2/rainbowtodolist/tasks/<uuid:task_id>")
def update_task(task_id):
    """ Updates task to have the properties given by the form. Returns that modified task. """
    text = request.form["text"]
    updated_task = applogic.update_task(task_id, text)
    return RainbowTasksResponse("Updated tasks", updated_task).serialize()


@bp.delete("/api/v0.2/rainbowtodolist/tasks/<uuid:task_id>")
def remove_task(task_id):
    """ Removes the specified task. Returns that removed task. """
    removed_task = applogic.remove_task(task_id)
    return RainbowTasksResponse("Task removed", removed_task).serialize()
