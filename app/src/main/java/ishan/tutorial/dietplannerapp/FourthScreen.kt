package ishan.tutorial.dietplannerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FourthScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    var isDiabetic by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image above the text
        Image(
            painter = painterResource(id = R.drawable.question),
            contentDescription = "Diabetic Icon",
            modifier = Modifier
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Are you diabetic?",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Yes/No Buttons
        Row {
            Button(
                onClick = {
                    sharedViewModel.isDiabetic.value = true  // Save to ViewModel
                    navController.navigate("fifth_screen")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)) // Blue color
            ) {
                Text("Yes", color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    sharedViewModel.isDiabetic.value = false  // Save to ViewModel
                    navController.navigate("sixth_screen")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00)) // Orange color
            ) {
                Text("No", color = Color.White)
            }
        }
    }
}
