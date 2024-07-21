from flask import Flask

def create_app():
    app = Flask(__name__)

    @app.route("/api/v0.2/rainbowtodolist")
    def index():
        return "Welcome to rainbow todolist!"

    return app

