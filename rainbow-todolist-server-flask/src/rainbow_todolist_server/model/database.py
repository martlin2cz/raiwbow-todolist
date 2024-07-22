import uuid
from typing import List

from ..model.entities import RainbowTask


class AbstractDatabase:
    def open_db(self):
        pass

    def close_db(self):
        pass

class InMemoryDatabase(AbstractDatabase):
    tasks: List[RainbowTask]

    def __init__(self):
        task_id = uuid.uuid4()
        task = RainbowTask(task_id, "Try the app")
        self.tasks = [task]
