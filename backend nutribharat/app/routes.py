from flask import Blueprint, request, jsonify
from app.utils import calculate_tdee, calculate_macros
from app.recommender import recommend_breakfast


main = Blueprint('main', __name__)

@main.route('/')
def home():
    return jsonify({
        'message': 'Welcome to Nutribharat API! This is the home route.',
        'endpoints': {
            'POST /calculate_macros': 'Calculate TDEE and macronutrients',
            'POST /recommend_breakfast': 'Get meal recommendations'
        }
    })

# Calculate Macros Route
@main.route('/calculate_macros', methods=['POST'])
def calculate_macros_route():
    # Get data from frontend (Kotlin)
    data = request.get_json()

    # Input validation
    if not data:
        return jsonify({'error': 'Invalid input. JSON body is required.'}), 400

    weight = data.get('weight')
    height = data.get('height')
    age = data.get('age')
    gender = data.get('gender')
    activity_level = data.get('activity_level')

    # Validate all required fields
    if not all([weight, height, age, gender, activity_level]):
        return jsonify({'error': 'Missing required fields'}), 400

    try:
        # Calculate TDEE
        tdee = calculate_tdee(weight, height, age, gender, activity_level)

        # Calculate macronutrients
        macros = calculate_macros(tdee)

        # Return the TDEE and macronutrient breakdown to the frontend
        return jsonify({
            'TDEE': tdee,
            'Macronutrients': macros
        })
    except Exception as e:
        return jsonify({'error': str(e)}), 500