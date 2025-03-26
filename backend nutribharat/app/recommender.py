import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import NearestNeighbors


food_df = pd.read_csv('./data/dataset.csv')

macronutrient_columns = ["unit_serving_energy_kcal", "unit_serving_carb_g", "unit_serving_protein_g", "unit_serving_fat_g"]
scaler = StandardScaler()
food_df[macronutrient_columns] = scaler.fit_transform(food_df[macronutrient_columns])


def knn_recommend(food_data, target_nutrients, n_neighbors=5, veg_filter=None):
    knn = NearestNeighbors(n_neighbors=n_neighbors)
    knn.fit(food_data[macronutrient_columns])

    distances, indices = knn.kneighbors([target_nutrients])
    recommendations = food_data.iloc[indices[0]]

    if veg_filter is not None:
        recommendations = recommendations[recommendations['Veg/Non Veg'] == veg_filter]

    return recommendations

def calculate_target_nutrients(daily_calories, daily_carbs, daily_protein, daily_fat, ratio):
    return (
        daily_calories * ratio,
        daily_carbs * ratio,
        daily_protein * ratio,
        daily_fat * ratio
    )

def recommend_breakfast(daily_calories, daily_carbs, daily_protein, daily_fat, is_vegetarian):
    target_calories, target_carbs, target_protein, target_fat = calculate_target_nutrients(
        daily_calories, daily_carbs, daily_protein, daily_fat, 0.25
    )
    target_nutrients = [target_calories, target_carbs, target_protein, target_fat]

    veg_filter = 'Veg' if is_vegetarian else None
    recommendations = knn_recommend(food_df, target_nutrients, veg_filter=veg_filter)

    return recommendations

def recommend_meal_combination(daily_calories, daily_carbs, daily_protein, daily_fat, is_vegetarian):
    meal_types = ['Soup', 'Bread', 'Curry', 'Dry Vegetable', 'Rice']
    combinations = []

    target_calories, target_carbs, target_protein, target_fat = calculate_target_nutrients(
        daily_calories, daily_carbs, daily_protein, daily_fat, 0.40
    )
    target_nutrients = [target_calories, target_carbs, target_protein, target_fat]

    veg_filter = 'Veg' if is_vegetarian else None

    for meal_type in meal_types:
        meal_items = food_df[food_df['Meal type'] == meal_type]

        if veg_filter:
            meal_items = meal_items[meal_items['Veg/Non Veg'] == veg_filter]

        if not meal_items.empty:
            top_item = knn_recommend(meal_items, target_nutrients, n_neighbors=1).iloc[0]
            combinations.append(top_item)

    total_nutrients = pd.DataFrame(combinations).sum()[macronutrient_columns]

    return combinations, total_nutrients


def recommend_diabetic_breakfast(daily_calories, daily_carbs, daily_protein, daily_fat, is_vegetarian, blood_sugar_level):
    # Adjust carb and sugar thresholds for diabetic users
    carb_ratio = 0.20 if blood_sugar_level == 'high' else 0.25
    sugar_limit = 5 if blood_sugar_level == 'high' else 10

    target_calories, target_carbs, target_protein, target_fat = calculate_target_nutrients(
        daily_calories, daily_carbs, daily_protein, daily_fat, carb_ratio
    )
    target_nutrients = [target_calories, target_carbs, target_protein, target_fat]

    diabetic_filter = (
        (food_df['unit_serving_freesugar_g'] <= sugar_limit) &
        (food_df['unit_serving_fibre_g'] >= 3)  # High fiber
    )

    if is_vegetarian:
        diabetic_filter &= food_df['Veg/Non Veg'] == 'Veg'

    diabetic_foods = food_df[diabetic_filter]

    if diabetic_foods.empty:
        return pd.DataFrame()  # No recommendations available

    recommendations = knn_recommend(diabetic_foods, target_nutrients)

    return recommendations




