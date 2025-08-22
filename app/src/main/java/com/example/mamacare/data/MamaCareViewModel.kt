package com.example.mamacare.data

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mamacare.model.ContactModel
import com.example.mamacare.model.MedicalInfoModel
import com.example.mamacare.navigation.ROUTE_HOMESCREEN
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MamaCareViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance().getReference("contacts")

    // ✅ Local observable state that Compose UI will automatically react to
    private val _contacts = mutableStateListOf<ContactModel>()
    val contacts: List<ContactModel> get() = _contacts

    init {
        fetchContacts() // fetch immediately when ViewModel is created
    }

    // ✅ Add or update a contact
    fun saveContact(
        contact: ContactModel,
        context: Context,
        navController: NavController? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val contactId = contact.contactId.ifEmpty { database.push().key ?: "" }
                contact.contactId = contactId
                database.child(contactId).setValue(contact).await()

                withContext(Dispatchers.Main) {
                    // update local state instantly
                    val existingIndex = _contacts.indexOfFirst { it.contactId == contactId }
                    if (existingIndex >= 0) {
                        _contacts[existingIndex] = contact
                    } else {
                        _contacts.add(contact)
                    }

                    Toast.makeText(context, "Contact saved successfully!", Toast.LENGTH_LONG).show()
                    navController?.navigate(ROUTE_HOMESCREEN) {
                        popUpTo(0)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Failed to save contact!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // ✅ Fetch all contacts from Firebase into local state
    fun fetchContacts() {
        database.get()
            .addOnSuccessListener { snapshot ->
                _contacts.clear()
                for (child in snapshot.children) {
                    val contact = child.getValue(ContactModel::class.java)
                    contact?.let {
                        it.contactId = child.key ?: ""
                        _contacts.add(it)
                    }
                }
            }
            .addOnFailureListener {
                // optional: handle errors here
            }
    }

    // ✅ Delete a contact
    fun deleteContact(contactId: String, context: Context) {
        database.child(contactId).removeValue()
            .addOnSuccessListener {
                _contacts.removeAll { it.contactId == contactId } // instantly remove from state
                Toast.makeText(context, "Contact deleted successfully!", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to delete contact!", Toast.LENGTH_LONG).show()
            }
    }

    // ✅ Fetch single contact (useful for Update screen)
    fun getContactById(contactId: String, onResult: (ContactModel?) -> Unit) {
        database.child(contactId).get()
            .addOnSuccessListener { snapshot ->
                val contact = snapshot.getValue(ContactModel::class.java)
                contact?.contactId = snapshot.key ?: ""
                onResult(contact)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    fun saveMedicalInfo(medicalInfo: MedicalInfoModel, context: Context, navController: NavController) {
        val ref = FirebaseDatabase.getInstance()
            .getReference("medical_info")
            .child(medicalInfo.id)

        ref.setValue(medicalInfo).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Medical info saved", Toast.LENGTH_LONG).show()
                navController.popBackStack() // Navigate back after saving
            } else {
                Toast.makeText(context, "Failed to save medical info", Toast.LENGTH_LONG).show()
            }
        }
    }




    fun fetchAllMedicalInfo(
        onSuccess: (List<MedicalInfoModel>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("medical_info")

        dbRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val list = mutableListOf<MedicalInfoModel>()
                for (child in snapshot.children) {
                    val info = child.getValue(MedicalInfoModel::class.java)
                    if (info != null) {
                        list.add(info)
                    }
                }
                onSuccess(list)
            } else {
                onSuccess(emptyList())
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }

    fun updateMedicalInfo(info: MedicalInfoModel, context: Context) {
        val ref = FirebaseDatabase.getInstance()
            .getReference("medical_info")
            .child(info.id)

        ref.setValue(info).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Medical info updated", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Failed to update", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun fetchMedicalInfo(
        id: String,
        onSuccess: (MedicalInfoModel) -> Unit,
        onError: () -> Unit
    ) {
        val ref = FirebaseDatabase.getInstance()
            .getReference("medical_info")
            .child(id)

        ref.get().addOnSuccessListener { snapshot ->
            val info = snapshot.getValue(MedicalInfoModel::class.java)
            if (info != null) {
                onSuccess(info)
            } else {
                onError()
            }
        }.addOnFailureListener {
            onError()
        }
    }

    fun deleteMedicalInfo(id: String, context: Context) {
        val ref = FirebaseDatabase.getInstance()
            .getReference("medical_info")
            .child(id)

        ref.removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Medical info deleted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Failed to delete", Toast.LENGTH_LONG).show()
            }
        }
    }




}


