from flask import Flask

def create_app():
    app = Flask(__name__)

    # Import the blueprint
    from app.routes import main
    
    # Register the blueprint
    app.register_blueprint(main)

    return app