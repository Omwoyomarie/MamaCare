package com.example.mamacare.ui.theme.Screens.MedicalInfo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.elevatedCardElevation
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

@Composable
fun ViewMedicalInfoScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: MamaCareViewModel = viewModel()

    var medicalInfoList by remember { mutableStateOf<List<MedicalInfoModel>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf<Pair<Boolean, String>>(false to "") }

    // Fetch all medical records
    LaunchedEffect(Unit) {
        viewModel.fetchAllMedicalInfo(
            onSuccess = { list ->
                medicalInfoList = list
                isLoading = false
            },
            onFailure = {
                isLoading = false
                Toast.makeText(context, "Failed to load records", Toast.LENGTH_SHORT).show()
            }
        )
    }

    val lightPurple = Color(0xFFE6E0F8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightPurple)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Top bar with back arrow and centered title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Medical Records",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (medicalInfoList.isEmpty()) {
            Text(
                "No medical info found.",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(medicalInfoList) { info ->

                    // Delete confirmation dialog
                    if (showDialog.first && showDialog.second == info.id) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false to "" },
                            title = { Text("Confirm Delete") },
                            text = { Text("Are you sure you want to delete this record?") },
                            confirmButton = {
                                TextButton(onClick = {
                                    viewModel.deleteMedicalInfo(info.id, context)
                                    medicalInfoList = medicalInfoList.filter { it.id != info.id }
                                    showDialog = false to ""
                                }) {
                                    Text("Yes", color = Color.Red)
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDialog = false to "" }) {
                                    Text("Cancel")
                                }
                            }
                        )
                    }

                    Card(
                        shape = RoundedCornerShape(12.dp),
                        elevation = elevatedCardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Blood Type: ${info.bloodType}", fontSize = 18.sp)
                            Text("Allergies: ${info.allergies}", fontSize = 18.sp)
                            Text("Conditions: ${info.conditions}", fontSize = 18.sp)
                            Text("Hospital: ${info.hospital}", fontSize = 18.sp)

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TextButton(
                                    onClick = {
                                        navController.navigate("update_medical_info/${info.id}")
                                    }
                                ) {
                                    Text("Update", color = Color(0xFF512DAB))
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                TextButton(onClick = {
                                    showDialog = true to info.id
                                }) {
                                    Text("Delete", color = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
