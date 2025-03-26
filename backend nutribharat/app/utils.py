def calculate_tdee(weight, height, age, gender, activity_level):
    """
    Calculate Total Daily Energy Expenditure (TDEE) using Mifflin-St Jeor Equation.
    """
    if gender == 'Male':
        bmr = 10 * weight + 6.25 * height - 5 * age + 5
    else:
        bmr = 10 * weight + 6.25 * height - 5 * age - 161

    # Activity level multipliers
    activity_multipliers = {
        'Sedentary': 1.2,
        'Moderate': 1.55,
        'Super': 1.9
    }
    
    tdee = bmr * activity_multipliers[activity_level]
    return tdee

def calculate_macros(tdee, health_goal):
    """
    Calculate macronutrient distribution based on TDEE and health goal.
    """
    if health_goal == 'Muscle Gain':
        protein_ratio = 0.30
        carb_ratio = 0.45
        fat_ratio = 0.25
    elif health_goal == 'Fat Loss':
        protein_ratio = 0.35
        carb_ratio = 0.30
        fat_ratio = 0.35
    else:  # Balanced Diet
        protein_ratio = 0.20
        carb_ratio = 0.50
        fat_ratio = 0.30

    # Calculate calories from each macronutrient
    protein_calories = tdee * protein_ratio
    carb_calories = tdee * carb_ratio
    fat_calories = tdee * fat_ratio

    # Convert calories to grams
    protein_grams = protein_calories / 4
    carb_grams = carb_calories / 4
    fat_grams = fat_calories / 9

    return {
        'Protein (g)': protein_grams,
        'Carbs (g)': carb_grams,
        'Fats (g)': fat_grams
    }