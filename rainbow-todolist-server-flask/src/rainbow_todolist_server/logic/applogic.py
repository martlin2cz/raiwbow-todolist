from ..model.entities import RainbowTask


def list_tasks():
    task = RainbowTask(42, "Try the app")
    return [task];