package ishan.tutorial.dietplannerapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifthScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    // Using the shared ViewModel for managing the diabetic data
    val fastingLevels by remember { sharedViewModel.fastingLevels }
    val postMealLevels by remember { sharedViewModel.postMealLevels }
    val hba1c by remember { sharedViewModel.hba1c }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Diabetic Information") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // "Next" button on the top right corner
                    TextButton(
                        onClick = {
                            // Pass fasting levels, post-meal levels, and HbA1c to the ViewModel
                            sharedViewModel.fastingLevels.value = fastingLevels
                            sharedViewModel.postMealLevels.value = postMealLevels
                            sharedViewModel.hba1c.value = hba1c

                            // Navigate to the next screen
                            navController.navigate("sixth_screen")
                        }
                    ) {
                        Text("Next", color = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Enter your last 3 fasting blood sugar levels (mg/dL):", style = MaterialTheme.typography.titleLarge)

                // Fasting Blood Sugar Levels Input
                fastingLevels.forEachIndexed { index, level ->
                    TextField(
                        value = level,
                        onValueChange = { newValue ->
                            sharedViewModel.fastingLevels.value = fastingLevels.toMutableList().also { it[index] = newValue }
                        },
                        placeholder = { Text("Fasting level ${index + 1}") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Text("Enter your last 3 post-meal blood sugar levels (mg/dL):", style = MaterialTheme.typography.titleLarge)

                // Post-meal Blood Sugar Levels Input
                postMealLevels.forEachIndexed { index, level ->
                    TextField(
                        value = level,
                        onValueChange = { newValue ->
                            sharedViewModel.postMealLevels.value = postMealLevels.toMutableList().also { it[index] = newValue }
                        },
                        placeholder = { Text("Post-meal level ${index + 1}") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                // HbA1c Input
                Text("Enter your latest HbA1c test percentage (%):", style = MaterialTheme.typography.titleLarge)
                TextField(
                    value = hba1c,
                    onValueChange = { sharedViewModel.hba1c.value = it },
                    placeholder = { Text("HbA1c percentage") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Next Button at Bottom
                Button(
                    onClick = {

                        sharedViewModel.fastingLevels.value = fastingLevels
                        sharedViewModel.postMealLevels.value = postMealLevels
                        sharedViewModel.hba1c.value = hba1c

                        navController.navigate("sixth_screen")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF)) // Blue color
                ) {
                    Text("Next", color = Color.White)
                }
            }
        }
    )
}
