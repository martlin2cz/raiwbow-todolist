import uuid

from ..model.database import InMemoryDatabase
from ..model.entities import RainbowTask


db = InMemoryDatabase()


def list_tasks():
    return db.tasks

def get_task(task_id):
    return db.get(task_id)

def add_task(text):
    task_id = uuid.uuid4()
    task = RainbowTask(task_id, text)
    db.add(task)
    return task

def update_task(task_id, new_text):
    task = db.get(task_id)
    updated_task = db.update(task, new_text)
    return updated_task
def remove_task(task_id):
    task = db.get(task_id)
    db.remove(task)
    return task
