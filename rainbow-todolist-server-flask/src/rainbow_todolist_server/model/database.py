import logging
import uuid
from typing import List

from ..model.entities import RainbowTask

logger = logging.getLogger("rainbow_todolist_server.model.database")

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
        print(str(task))
        self.tasks = [task]

    def get(self, task_id):
        try:
            return [task for task in self.tasks if task.id == task_id][0]
        except IndexError:
            raise ValueError("No such task")

    def add(self, task):
        self.tasks.append(task)
        logger.info("Created task %s", task)

    def update(self, task, new_text):
        task.text = new_text
        logger.info("Updated task %s", task)
        return task

    def remove(self, task):
        self.tasks.remove(task)
        logger.info("Removed task %s", task)
