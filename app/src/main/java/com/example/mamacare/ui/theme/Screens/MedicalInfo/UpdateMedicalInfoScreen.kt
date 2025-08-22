package com.example.mamacare.ui.theme.Screens.MedicalInfo

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavBackStackEntry
import com.example.mamacare.data.MamaCareViewModel
import com.example.mamacare.model.MedicalInfoModel

@Composable
fun UpdateMedicalInfoScreen(
    navController: NavController,
    backStackEntry: NavBackStackEntry
) {
    val context = LocalContext.current
    val viewModel: MamaCareViewModel = viewModel()

    // Get the record ID from navigation arguments
    val recordId = backStackEntry.arguments?.getString("id") ?: return

    var bloodType by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    var conditions by remember { mutableStateOf("") }
    var hospital by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch the existing record
    LaunchedEffect(recordId) {
        viewModel.fetchMedicalInfo(
            recordId,
            onSuccess = { info ->
                bloodType = info.bloodType
                allergies = info.allergies
                conditions = info.conditions
                hospital = info.hospital
                isLoading = false
            },
            onError = {
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
        )
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Update Medical Info",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = bloodType,
                onValueChange = { bloodType = it },
                label = { Text("Blood Type") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = allergies,
                onValueChange = { allergies = it },
                label = { Text("Allergies") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = conditions,
                onValueChange = { conditions = it },
                label = { Text("Conditions") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = hospital,
                onValueChange = { hospital = it },
                label = { Text("Hospital") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val updatedInfo = MedicalInfoModel(recordId, bloodType, allergies, conditions, hospital)
                    viewModel.updateMedicalInfo(updatedInfo, context)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF512DAB))
            ) {
                Text("Update", color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go Back")
            }
        }
    }
}
