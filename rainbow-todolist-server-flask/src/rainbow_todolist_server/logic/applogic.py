import uuid

from ..model.database import InMemoryDatabase
from ..model.entities import RainbowTask


db = InMemoryDatabase()


def list_tasks():
    return db.tasks
