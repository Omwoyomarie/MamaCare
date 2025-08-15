//package com.example.mamacare.ui.screens
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun HomeScreen(navController: NavController) {
//    var contactName by remember { mutableStateOf("") }
//    var contactPhone by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF5F5F5))
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Text(
//            text = "Emergency Assistance",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
//        )
//
//        // SOS Circle Button
//        Button(
//            onClick = { /* Handle SOS */ },
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
//            shape = CircleShape,
//            modifier = Modifier
//                .size(200.dp)
//                .padding(8.dp)
//        ) {
//            Text(
//                "SOS",
//                fontSize = 48.sp,
//                color = Color.White,
//                fontWeight = FontWeight.ExtraBold,
//                textAlign = TextAlign.Center
//            )
//        }
//
//        Spacer(modifier = Modifier.height(40.dp))
//
//        Text(
//            text = "Add Emergency Contact",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = contactName,
//            onValueChange = { contactName = it },
//            label = { Text("Contact Name") },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = contactPhone,
//            onValueChange = { contactPhone = it },
//            label = { Text("Contact Phone") },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Button(
//            onClick = { /* Save Contact */ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Text("Save Contact", fontSize = 16.sp)
//        }
//    }
//}
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(rememberNavController())
//}
package com.example.mamacare.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    userName: String = "Mama"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Greeting
        Text(
            text = "Hi $userName,",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "Welcome to MamaCare",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 32.dp)
        )

        // SOS Button
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red, shape = CircleShape)
                .clickable {
                    navController.navigate("sos_screen")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "SOS",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Row 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DashboardButton("Contacts") {
                navController.navigate("contacts_screen")
            }
            DashboardButton("Medical Info") {
                navController.navigate("medical_info_screen")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Row 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            DashboardButton("Contraction Timer") {
                navController.navigate("contraction_timer_screen")
            }
        }
    }
}

@Composable
fun DashboardButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 80.dp)
            .background(Color(0xFFFFF3E0), shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}
