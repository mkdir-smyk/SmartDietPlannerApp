package ishan.tutorial.dietplannerapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhysicalProfileScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var isFormValid by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf(false) }
    var heightError by remember { mutableStateOf(false) }
    var weightError by remember { mutableStateOf(false) }
    var ageError by remember { mutableStateOf(false) }

    fun validateForm() {
        nameError = sharedViewModel.name.value.isBlank()
        heightError = sharedViewModel.height.value.isBlank()
        weightError = sharedViewModel.weight.value.isBlank()
        ageError = sharedViewModel.age.value.isBlank()
        isFormValid = !nameError && !heightError && !weightError && !ageError
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Physical Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Nutri Bharat uses your calorie budget, height, weight, biological sex, and age as inputs.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )

                // Name Input
                Text("Name", style = MaterialTheme.typography.titleLarge)
                TextField(
                    value = sharedViewModel.name.value,
                    onValueChange = {
                        sharedViewModel.name.value = it
                        validateForm()
                    },
                    placeholder = { Text("Enter Name") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = nameError
                )
                if (nameError) {
                    Text("Name is required", color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                // Height Input
                Text("Height", style = MaterialTheme.typography.titleLarge)
                TextField(
                    value = sharedViewModel.height.value,
                    onValueChange = {
                        sharedViewModel.height.value = it
                        validateForm()
                    },
                    placeholder = { Text("Enter height (in cm)") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = heightError
                )
                if (heightError) {
                    Text("Height is required", color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                // Weight Input
                Text("Weight", style = MaterialTheme.typography.titleLarge)
                TextField(
                    value = sharedViewModel.weight.value,
                    onValueChange = {
                        sharedViewModel.weight.value = it
                        validateForm()
                    },
                    placeholder = { Text("Enter weight (in kg)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = weightError
                )
                if (weightError) {
                    Text("Weight is required", color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                // Age Input
                Text("Age", style = MaterialTheme.typography.titleLarge)
                TextField(
                    value = sharedViewModel.age.value,
                    onValueChange = {
                        sharedViewModel.age.value = it
                        validateForm()
                    },
                    placeholder = { Text("Enter age") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = ageError
                )
                if (ageError) {
                    Text("Age is required", color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                // Biological Sex Selection
                Text("Biological Sex", style = MaterialTheme.typography.titleLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    listOf("Female", "Male", "Other").forEach { sex ->
                        Button(
                            onClick = {
                                sharedViewModel.selectedSex.value = sex
                                validateForm()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (sharedViewModel.selectedSex.value == sex) Color(0xFF2196F3) else Color.Gray
                            )
                        ) {
                            Text(sex)
                        }
                    }
                }

                // Activity Level Selection
                Text("Activity Level", style = MaterialTheme.typography.titleLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    listOf("Sedentary", "Moderate", "Super").forEach { level ->
                        Button(
                            onClick = {
                                sharedViewModel.selectedActivityLevel.value = level
                                validateForm()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (sharedViewModel.selectedActivityLevel.value == level) Color(0xFFFFA500) else Color.Gray
                            )
                        ) {
                            Text(level)
                        }
                    }
                }

                // Dietary Preference Section
                Text("Select your dietary preference:", style = MaterialTheme.typography.titleLarge)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    listOf("Veg", "Non-Veg").forEach { diet ->
                        Button(
                            onClick = {
                                sharedViewModel.selectedDiet.value = diet
                                validateForm()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (sharedViewModel.selectedDiet.value == diet) {
                                    if (diet == "Veg") Color(0xFF4CAF50) else Color(0xFFFF5252)
                                } else Color.Gray
                            )
                        ) {
                            Text(diet, color = Color.White)
                        }
                    }
                }

                // Health Goals Selection
                Text("Health Goals", style = MaterialTheme.typography.titleLarge)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    listOf("Muscle gain", "Fat loss", "Others...").forEach { goal ->
                        Button(
                            onClick = {
                                sharedViewModel.selectedHealthGoals.value = goal
                                validateForm()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (sharedViewModel.selectedHealthGoals.value == goal) Color(0xFFFFA500) else Color.Gray
                            )
                        ) {
                            Text(goal)
                        }
                    }
                }

                // Submit Button
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        if (isFormValid) {
                            navController.navigate("food_entry_screen")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = if (isFormValid) Color(0xFFFF6D00) else Color.Gray),
                    enabled = isFormValid
                ) {
                    Text("Submit", color = Color.White)
                }
            }
        }
    )
}
