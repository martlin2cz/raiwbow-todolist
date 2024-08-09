import uuid
from typing import List

from ..model.database import InMemoryDatabase
from ..model.entities import RainbowTask


db = InMemoryDatabase()


def list_tasks() -> List[RainbowTask]:
    """ Lists all the tasks. """
    return db.tasks


def get_task(task_id: uuid) -> RainbowTask:
    """ Returns the task with the specified id. """
    return db.get(task_id)


def add_task(text: str) -> RainbowTask:
    """ Adds the task with the specified text. """
    task_id = uuid.uuid4()
    task = RainbowTask(task_id, text)
    db.add(task)
    return task


def update_task(task_id: uuid, new_text: str) -> RainbowTask:
    """ Updates the task to have the new text. """
    task = db.get(task_id)
    updated_task = db.update(task, new_text)
    return updated_task


def remove_task(task_id: uuid) -> RainbowTask:
    """ Removes the task. """
    task = db.get(task_id)
    db.remove(task)
    return task
