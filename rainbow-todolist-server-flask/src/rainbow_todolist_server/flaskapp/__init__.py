from flask import Flask
from flask import request
from flask_cors import CORS

from ..logic import applogic


def create_app():
    app = Flask(__name__)
    CORS(app)

    from .routes import bp
    app.register_blueprint(bp)

    @app.route("/api/v0.2/rainbowtodolist")
    def index() -> str:
        """ The 'testing' hello service. """
        return "Welcome to rainbow todolist!"

    return app

