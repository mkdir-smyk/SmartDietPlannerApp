package ishan.tutorial.dietplannerapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Logo
        BasicText(
            text = "Nutri Bharat",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 32.sp,
                color = Color(0xFFFFA500),
                textAlign = TextAlign.Center
            )
        )

        //use to create empty spaces b/w comp in layout
        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Just Eat!",
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Get Started Button - Navigate to PhysicalProfileScreen
        Button(
            onClick = { navController.navigate("physical_profile_screen") },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
        ) {
            Text(text = "Get Started")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Already Have an Account Button - Navigate to InprogressScreen
        Button(
            onClick = { navController.navigate("inprogress_screen") },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF))
        ) {
            Text(text = "I already have an account")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Try Our Generator Button - Navigate to InprogressScreen
        Button(
            onClick = { navController.navigate("inprogress_screen") },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF))
        ) {
            Text(text = "Try our generator now!")
        }
    }
}
