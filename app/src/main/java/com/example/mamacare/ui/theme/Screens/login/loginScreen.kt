package com.example.mamacare.ui.theme.Screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mamacare.R
import com.example.mamacare.data.AuthViewModel
import com.example.mamacare.navigation.ROUTE_REGISTER

@Composable
fun loginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authViewModel: AuthViewModel = viewModel()
    Box() {
        Image(
            painter = painterResource(id = R.drawable.newborn),
            contentDescription = "register background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Login Here",
                fontSize = 40.sp,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.wallpaper),
//                contentDescription = "Image wallpaper",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(80.dp),
//                contentScale = ContentScale.Fit
//            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter email") },
                placeholder = { Text("Please enter email",color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email icon") },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Black
                )
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter password") },
                placeholder = { Text("Please enter password", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "password") },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            val context = LocalContext.current
            Button(onClick = {
                authViewModel.login(email=email,password=password,navController=navController, context = context)
            },
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier.fillMaxWidth(0.8f))
            { Text(text = "login", color = Color.White) }

            Text(text = "If not registered,register here",
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.clickable { navController.navigate(ROUTE_REGISTER) })



        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun loginScreenPreview(){
    loginScreen(rememberNavController())
}