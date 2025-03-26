package ishan.tutorial.dietplannerapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import ishan.tutorial.dietplannerapp.ui.theme.DietPlannerAppTheme
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.HttpException

class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    if (account != null) {
                        Log.d("MainActivity", "Sign-in successful: ${account.email}")
                    }
                } catch (e: ApiException) {
                    Log.w("MainActivity", "Sign-in failed: ${e.statusCode}")
                    Toast.makeText(this, "Sign-In Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Check if a user is already signed in
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null) {
            signIn()
        }

        // Set content using Jetpack Compose
        setContent {
            DietPlannerAppTheme {
                val navController = rememberNavController()
                val sharedViewModel: SharedViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "first_screen"
                ) {
                    composable("first_screen") { FirstScreen(navController) }
                    composable("physical_profile_screen") { PhysicalProfileScreen(navController, sharedViewModel)}
                    composable("food_entry_screen") { FoodEntryScreen(navController) }
                    composable("fourth_screen") { FourthScreen(navController, sharedViewModel) }
                    composable("fifth_screen") { FifthScreen(navController = navController, sharedViewModel = sharedViewModel) }
                    composable("sixth_screen") { SixthScreen(navController, ::navigateToSeventhScreen) }
                    composable("seventh_screen") {
                        SeventhScreen(navController = navController, sharedViewModel = sharedViewModel)}
                    composable("eight_screen") {
                        EightScreen(navController = navController, sharedViewModel = sharedViewModel)}
                    composable("second_screen") {
                        PhysicalProfileScreen(navController = navController, sharedViewModel = sharedViewModel)
                    }
                }
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun isValidPhysicalData(height: String, weight: String, age: String): Boolean {
        return height.isNotEmpty() && height.toIntOrNull() != null &&
                weight.isNotEmpty() && weight.toIntOrNull() != null &&
                age.isNotEmpty() && age.toIntOrNull() != null
    }

    fun savePhysicalProfileData(height: String, weight: String, sex: String, age: String, activityLevel: String, diet: String) {
        if (!isValidPhysicalData(height, weight, age)) {
            Toast.makeText(this, "Please enter valid physical profile data", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("MainActivity", "Physical Profile Data: $height, $weight, $sex, $age, $activityLevel, $diet")

        val macroRequest = MacroRequest(
            weight = weight.toInt(),
            height = height.toInt(),
            age = age.toInt(),
            gender = sex,
            activityLevel = "moderate"
        )

        calculateMacros(macroRequest)
    }

    private fun calculateMacros(macroRequest: MacroRequest) {
        lifecycleScope.launch {
            try {
                val response: Response<MacroResponse> = RetrofitInstance.api.calculateMacros(macroRequest)

                if (response.isSuccessful) {
                    val macros = response.body()
                    macros?.let {
                        Log.d("MainActivity", "TDEE: ${it.TDEE}")
                        val protein = it.Macronutrients["protein"] ?: 0.0
                        val carbs = it.Macronutrients["carbs"] ?: 0.0
                        val fat = it.Macronutrients["fat"] ?: 0.0

                        Toast.makeText(
                            this@MainActivity,
                            "TDEE: ${it.TDEE}, Protein: $protein, Carbs: $carbs, Fat: $fat",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    handleApiError(response.message())
                }
            } catch (e: HttpException) {
                handleApiError("HTTP Exception: ${e.message()}")
            } catch (e: java.net.SocketTimeoutException) {
                handleApiError("Server timeout. Please try again.")
            } catch (e: Exception) {
                handleApiError("Unexpected Error: ${e.message}")
            }
        }
    }

    private fun handleApiError(message: String) {
        Log.e("MainActivity", message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun saveDietaryPreferenceData(diet: String, calorieIntake: String) {
        Log.d("MainActivity", "Dietary Preference Data: $diet, $calorieIntake")
    }

    fun saveDiabeticData(fastingLevels: List<String>, postMealLevels: List<String>, hba1c: String) {
        Log.d("MainActivity", "Diabetic Data: $fastingLevels, $postMealLevels, $hba1c")
    }

    fun navigateToSeventhScreen(navController: NavController) {
        navController.navigate("seventh_screen")
    }
}
