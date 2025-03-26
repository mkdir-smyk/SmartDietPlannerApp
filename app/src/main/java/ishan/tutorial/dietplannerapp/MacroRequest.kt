package ishan.tutorial.dietplannerapp

data class MacroRequest(
    val weight: Int,
    val height: Int,
    val age: Int,
    val gender: String,
    val activityLevel: String
)

data class MacroResponse(
    val TDEE: Double,
    val Macronutrients: Map<String, Double> // Map to store macronutrients (protein, carbs, fat)
)
