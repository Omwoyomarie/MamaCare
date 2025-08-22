package com.example.mamacare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mamacare.navigation.ROUTE_CONTACT
import com.example.mamacare.navigation.ROUTE_MEDICAL_INFO
import com.example.mamacare.navigation.ROUTE_VIEW_CONTACTS
import com.example.mamacare.navigation.ROUTE_VIEW_MEDICAL_INFO

@Composable
fun HomeScreen(navController: NavController) {
    val lightPurple = Color(0xFFD9CBFF)
    val whiteBackground = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(whiteBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp)) // Push content slightly lower

        // App Title
        Text(
            text = "MamaCare",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Greeting
        Text(
            text = "Hi Mama",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // SOS Button with shadow
        Surface(
            modifier = Modifier
                .size(200.dp)
                .clickable { navController.navigate("sos_screen") },
            shape = CircleShape,
            color = Color.Red,
            shadowElevation = 8.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "SOS Icon",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = "SOS",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Dashboard Buttons
        DashboardButton(
            icon = Icons.Filled.Person,
            label = "Add Contacts",
            backgroundColor = lightPurple
        ) { navController.navigate(ROUTE_CONTACT) }

        Spacer(modifier = Modifier.height(16.dp))

        DashboardButton(
            icon = Icons.Filled.ListAlt,
            label = "View Contacts",
            backgroundColor = lightPurple
        ) { navController.navigate(ROUTE_VIEW_CONTACTS) }

        Spacer(modifier = Modifier.height(16.dp))

        DashboardButton(
            icon = Icons.Filled.Add,
            label = "Add Medical Info",
            backgroundColor = lightPurple
        ) { navController.navigate(ROUTE_MEDICAL_INFO) }

        Spacer(modifier = Modifier.height(16.dp))

        DashboardButton(
            icon = Icons.Filled.ListAlt,
            label = "View Medical Info",
            backgroundColor = lightPurple
        ) { navController.navigate(ROUTE_VIEW_MEDICAL_INFO) }

        Spacer(modifier = Modifier.height(16.dp))

        DashboardButton(
            icon = Icons.Filled.Timer,
            label = "Contraction Timer",
            backgroundColor = lightPurple
        ) { navController.navigate("contraction_timer_screen") }
    }
}

@Composable
fun DashboardButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        shadowElevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}
