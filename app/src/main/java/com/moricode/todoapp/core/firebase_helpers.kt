package com.moricode.todoapp.core

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

suspend fun FirebaseFirestore.saveDataInFireStore(
    childName: String,
    hashMap: HashMap<String, Any>
): Boolean {
    return try {
        this
            .collection("todo")
            .document(childName)
            .set(hashMap)
            .await()
        true
    } catch (e: Exception) {
        false
    }
}

suspend fun FirebaseFirestore.getAllDocuments()
        : List<DocumentSnapshot>? {
    return try {
        val data = this
            .collection("todo")
            .get()
            .await()
        data.documents
    } catch (e: Exception) {
        null
    }
}