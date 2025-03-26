package ishan.tutorial.dietplannerapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    // User Input States
    var name = mutableStateOf("")
    var height = mutableStateOf("") // Height in cm
    var weight = mutableStateOf("") // Weight in kg
    var age = mutableStateOf("")    // Age in years
    var selectedSex = mutableStateOf("Male") // "Male" or "Female"
    var selectedActivityLevel = mutableStateOf("Sedentary") // Sedentary, Moderate, Super
    var selectedHealthGoals = mutableStateOf("Fat loss") // Fat loss, Maintenance, etc.
    var selectedDiet = mutableStateOf("Veg") // Veg, Non-Veg

    // Diabetic Information
    var fastingLevels = mutableStateOf(List(3) { "" }) // List for fasting glucose levels
    var postMealLevels = mutableStateOf(List(3) { "" }) // List for post-meal glucose levels
    var hba1c = mutableStateOf("") // HbA1c value
    var isDiabetic = mutableStateOf(false) // True if user is diabetic

    // TDEE Calculation Result
    var tdeeResult = mutableStateOf("")

    // Function to calculate TDEE
    fun calculateTDEE() {
        val weightValue = weight.value.toDoubleOrNull()
        val heightValue = height.value.toDoubleOrNull()
        val ageValue = age.value.toIntOrNull()

        if (weightValue != null && heightValue != null && ageValue != null &&
            weightValue > 0 && heightValue > 0 && ageValue > 0
        ) {
            // Valid inputs; calculate TDEE
            val tdee = calculateTDEE(weightValue, heightValue, ageValue, selectedSex.value, selectedActivityLevel.value)
            tdeeResult.value = "TDEE: %.2f kcal/day".format(tdee)
        } else {
            // Invalid input; show error
            tdeeResult.value = "Please enter valid positive values for weight, height, and age."
        }
    }

    // Private method to calculate TDEE using BMR and activity level multipliers
    private fun calculateTDEE(weight: Double, height: Double, age: Int, selectedSex: String, activityLevel: String): Double {
        // Basal Metabolic Rate (BMR) Calculation
        val bmr = if (selectedSex == "Male") {
            10 * weight + 6.25 * height - 5 * age + 5
        } else {
            10 * weight + 6.25 * height - 5 * age - 161
        }

        // Activity level multipliers
        val activityMultipliers = mapOf(
            "Sedentary" to 1.2,  // Little to no exercise
            "Moderate" to 1.55,  // Moderate exercise 3-5 times a week
            "Super" to 1.9       // Hard exercise or physical job
        )

        // Safely retrieve multiplier or default to 1.2
        val multiplier = activityMultipliers[activityLevel] ?: 1.2

        // Return calculated TDEE
        return bmr * multiplier
    }
}
