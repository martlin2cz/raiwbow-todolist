import uuid
from dataclasses import dataclass


@dataclass()
class RainbowTask:
    id: uuid
    text: str

