package com.example.mamacare.ui.theme.Screens.MedicalInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mamacare.data.MamaCareViewModel
import com.example.mamacare.model.MedicalInfoModel
import java.util.UUID

@Composable
fun MedicalInfoScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: MamaCareViewModel = viewModel()

    val userId = "user123"

    var bloodType by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    var conditions by remember { mutableStateOf("") }
    var hospital by remember { mutableStateOf("") }

    // Colors
    val lightPurple = Color(0xFFE6E0F8)
    val darkPurple = Color(0xFF512DAB)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightPurple)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Medical Info",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = bloodType,
            onValueChange = { bloodType = it },
            label = { Text("Blood Type") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        OutlinedTextField(
            value = allergies,
            onValueChange = { allergies = it },
            label = { Text("Allergies") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        OutlinedTextField(
            value = conditions,
            onValueChange = { conditions = it },
            label = { Text("Conditions") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        OutlinedTextField(
            value = hospital,
            onValueChange = { hospital = it },
            label = { Text("Hospital") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(24.dp)) // Space before buttons

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val id = UUID.randomUUID().toString()
                    val medicalInfo = MedicalInfoModel(id, bloodType, allergies, conditions, hospital)
                    viewModel.saveMedicalInfo(medicalInfo, context, navController) // pass navController
                },
                colors = ButtonDefaults.buttonColors(containerColor = darkPurple),
                modifier = Modifier.weight(1f)
            ) {
                Text("Save", color = Color.White)
            }
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = darkPurple),
                modifier = Modifier.weight(1f)
            ) {
                Text("Go Back", color = Color.White)
            }
        }
    }
}
