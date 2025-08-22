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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mamacare.data.MamaCareViewModel
import com.example.mamacare.model.ContactModel
import kotlinx.coroutines.launch

@Composable
fun UpdateContactsScreen(
    navController: NavController,
    contactId: String
) {
    val viewModel: MamaCareViewModel = viewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var contact by remember { mutableStateOf<ContactModel?>(null) }

    // Fetch the contact to edit
    LaunchedEffect(contactId) {
        viewModel.getContactById(contactId) { fetchedContact ->
            contact = fetchedContact
        }
    }

    // Only show fields when contact is loaded
    contact?.let { existingContact ->
        var name by remember { mutableStateOf(existingContact.name) }
        var phoneNumber by remember { mutableStateOf(existingContact.phoneNumber) }
        var relation by remember { mutableStateOf(existingContact.relation) }
        var isSaving by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF512DAB))
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Update Contact",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )

                // Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp)
                )

                // Phone
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )

                // Relation
                OutlinedTextField(
                    value = relation,
                    onValueChange = { relation = it },
                    label = { Text("Relation") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { navController.popBackStack() }) {
                        Text(text = "GO BACK")
                    }

                    Button(onClick = {
                        if (name.isNotBlank() && phoneNumber.isNotBlank() && relation.isNotBlank()) {
                            isSaving = true
                            scope.launch {
                                val updatedContact = existingContact.copy(
                                    name = name,
                                    phoneNumber = phoneNumber,
                                    relation = relation
                                )
                                viewModel.saveContact(updatedContact, context, navController)
                                isSaving = false
                            }
                        }
                    }) {
                        Text(if (isSaving) "Saving..." else "Update Contact")
                    }
                }
            }
        }
    }
}
