package com.moricode.todoapp.core

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.core.base.handleError
import kotlinx.coroutines.tasks.await
import java.net.SocketException

suspend fun FirebaseFirestore.saveDataInFireStore(
    childName: String,
    hashMap: HashMap<String, Any?>
): Resource<Boolean> {
    return try {
        this
            .collection("todo")
            .document(childName)
            .set(hashMap)
            .await()
        Resource.success(true)
    } catch (e: Exception) {
        handleError(e)
    }
}

suspend fun FirebaseFirestore.getAllDocuments()
        : Resource<List<DocumentSnapshot>?> {
    return try {
        val data = this
            .collection("todo")
            .get()
            .await()
        Resource.success(data.documents)
    } catch (e: Exception) {
        handleError(e)
    }
}