package ishan.tutorial.dietplannerapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeventhScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PROFILE") },
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Entered Data: ",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Display data from SharedViewModel
                Text("Name: ${sharedViewModel.name.value}")
                Text("Height: ${sharedViewModel.height.value}")
                Text("Weight: ${sharedViewModel.weight.value}")
                Text("Age: ${sharedViewModel.age.value}")
                Text("Sex: ${sharedViewModel.selectedSex.value}")
                Text("Activity Level: ${sharedViewModel.selectedActivityLevel.value}")
                Text("Diet: ${sharedViewModel.selectedDiet.value}")
                Text("Health Goals: ${sharedViewModel.selectedHealthGoals.value}")

                Spacer(modifier = Modifier.height(16.dp))

                // TDEE Result
                Text(sharedViewModel.tdeeResult.value, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                // Calculate TDEE Button
                Button(
                    onClick = { sharedViewModel.calculateTDEE() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green color
                ) {
                    Text("++  Calculate TDEE  ++", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))


                // Edit Button
                Button(
                    onClick = { navController.navigate("second_screen") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFE0)) // Light Yellow
                ) {
                    Text("Edit ✏️", color = Color.Black) // Black text
                }



                Spacer(modifier = Modifier.height(16.dp))

                // Navigate to next screen Button
                Button(
                    onClick = { navController.navigate("eight_screen") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD00000))
                ) {
                    Text("Next ⏭️", color = Color.White)
                }
            }
        }
    )
}
