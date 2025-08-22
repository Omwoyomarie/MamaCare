package com.example.mamacare.ui.theme.Screens.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mamacare.data.MamaCareViewModel
import com.example.mamacare.model.ContactModel
import kotlinx.coroutines.launch

@Composable
fun AddContactsScreen(navController: NavController) {
    // ✅ Get your ViewModel inside the composable
    val viewModel: MamaCareViewModel = viewModel()

    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var relation by remember { mutableStateOf("") }

    var isSaving by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val darkPurple = Color(0xFF512DAB)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD1C4E9))
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Add Emergency Contact",
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            // Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name",color = Color.Black) },
                placeholder = { Text("Please Enter Your Name", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            // Phone
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number",color = Color.Black) },
                placeholder = { Text("Please Enter Phone Number", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            // Relation
            OutlinedTextField(
                value = relation,
                onValueChange = { relation = it },
                label = { Text("Relation", color = Color.Black) },
                placeholder = { Text("Relation e.g. cousin, husband, etc.", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            // Buttons
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = darkPurple),
                    onClick = { navController.popBackStack() }) {
                    Text(text = "GO BACK",color = Color.White)
                }

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = darkPurple),
                    onClick = {
                        if (name.isNotBlank() && phoneNumber.isNotBlank() && relation.isNotBlank()) {
                            isSaving = true
                            scope.launch {
                                val contact = ContactModel(
                                    name = name,
                                    phoneNumber = phoneNumber,
                                    relation = relation
                                )
                                viewModel.saveContact(contact, context, navController)
                                isSaving = false
                            }
                        }
                    }
                ) {
                    Text(if (isSaving) "Saving..." else "Save Contact",color = Color.White)
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddContactsScreenPreview() {
    AddContactsScreen(rememberNavController())
}
