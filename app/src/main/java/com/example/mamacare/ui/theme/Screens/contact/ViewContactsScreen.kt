package com.example.mamacare.ui.theme.Screens.contact

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.mamacare.model.ContactModel

@Composable
fun ViewContactsScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: MamaCareViewModel = viewModel()

    // ✅ Observe contacts directly from ViewModel
    val contacts = viewModel.contacts

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Emergency Contacts",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF512DAB),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (contacts.isEmpty()) {
                Text(
                    text = "No contacts found",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(contacts) { contact ->
                        var showDialog by remember { mutableStateOf(false) }

                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text("Confirm Delete") },
                                text = { Text("Are you sure you want to delete this contact?") },
                                confirmButton = {
                                    TextButton(onClick = {
                                        showDialog = false
                                        viewModel.deleteContact(contact.contactId, context)
                                    }) {
                                        Text("Yes", color = Color.Red)
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { showDialog = false }) {
                                        Text("Cancel")
                                    }
                                }
                            )
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFD1C4E9))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Name: ${contact.name}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = "Phone: ${contact.phoneNumber}")
                                Text(text = "Relation: ${contact.relation}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextButton(
                                        onClick = {
                                            // ✅ Navigate correctly with contactId
                                            navController.navigate("update_contacts/${contact.contactId}")
                                        }
                                    ) {
                                        Text("Update", color = Color(0xFF512DAB))
                                    }

                                    TextButton(onClick = { showDialog = true }) {
                                        Text("Delete", color = Color.Red)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Go Back")
            }
        }
    }
}



//package com.example.mamacare.ui.theme.Screens.contact
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.mamacare.data.MamaCareViewModel
//import com.example.mamacare.model.ContactModel
//
//@Composable
//fun ViewContactsScreen(navController: NavController) {
//    val context = LocalContext.current
//    val viewModel: MamaCareViewModel = viewModel()   // ✅ create here
//    var contacts by remember { mutableStateOf(listOf<ContactModel>()) }
//
//    // Fetch contacts from Firebase
//    LaunchedEffect(Unit) {
//        viewModel.fetchContacts(
//            onSuccess = {
//                contacts = it
//            },
//            onError = {
//                Toast.makeText(context, "Failed to load contacts", Toast.LENGTH_LONG).show()
//            }
//        )
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(16.dp)
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Text(
//                text = "Emergency Contacts",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF512DAB),
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            if (contacts.isEmpty()) {
//                Text(
//                    text = "No contacts found",
//                    color = Color.Gray,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            } else {
//                LazyColumn {
//                    items(contacts) { contact ->
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(8.dp),
//                            colors = CardDefaults.cardColors(containerColor = Color(0xFFD1C4E9))
//                        ) {
//                            Column(modifier = Modifier.padding(16.dp)) {
//                                Text("Name: ${contact.name}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                                Text("Phone: ${contact.phoneNumber}")
//                                Text("Relation: ${contact.relation}")
//                            }
//                        }
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Button(
//                onClick = { navController.popBackStack() },
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Text("Go Back")
//            }
//        }
//    }
//}
