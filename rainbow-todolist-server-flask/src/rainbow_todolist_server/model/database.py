import logging
import uuid
from typing import List

from ..model.entities import RainbowTask

logger = logging.getLogger(__name__)


class AbstractDatabase:
    """ The abstract database. """

    def open_db(self):
        """ Opens the connection. Override to do the job. """
        pass

    def close_db(self):
        """ Closes the connection. Override to do the job. """
        pass


class InMemoryDatabase(AbstractDatabase):
    """ The trivial in-memory database. """
    tasks: List[RainbowTask]

    def __init__(self):
        """ Prepares (pre-fills with initial 'hello' task). """
        task_id = uuid.uuid4()
        task = RainbowTask(task_id, "Try the app")
        print(str(task))
        self.tasks = [task]

    def get(self, task_id: uuid) -> RainbowTask:
        """ Returns the task of the given id. Fails if no such. """
        try:
            return [task for task in self.tasks if task.id == task_id][0]
        except IndexError:
            raise ValueError("No such task")

    def add(self, task: RainbowTask) -> None:
        """ Adds the specified task. """
        self.tasks.append(task)
        logger.info("Created task %s", task)

    def update(self, task: RainbowTask, new_text: str) -> RainbowTask:
        """ Updates the text specified task. """
        task.text = new_text
        logger.info("Updated task %s", task)
        return task

    def remove(self, task: RainbowTask) -> None:
        """ Removes the specified task. """
        self.tasks.remove(task)
        logger.info("Removed task %s", task)
