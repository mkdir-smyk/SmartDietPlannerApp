package ishan.tutorial.dietplannerapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SixthScreen(navController: NavController, onNext: (NavController) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Your data is stored and a diet planner is being generated.",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onNext(navController) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF))
        ) {
            Text("NEXT ...", color = Color.White)
        }
    }
}
