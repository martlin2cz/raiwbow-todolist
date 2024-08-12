import uuid
from dataclasses import dataclass


@dataclass()
class RainbowTask:
    """
    The rainbow todolist task.
    Attributes:
        id: the uuid
        text: the actual task text
    """
    id: uuid
    text: str

