package com.moricode.todoapp.core.util

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.core.base.handleError
import kotlinx.coroutines.tasks.await

const val COLLECTION_KEY_NAME = "todo"

suspend fun FirebaseFirestore.saveDataInFireStore(
    childName: String,
    data: HashMap<String, Any?>
): Resource<Boolean> {
    return try {
        this
            .collection(COLLECTION_KEY_NAME)
            .document(childName)
            .set(data)
            .await()
        Resource.success(true)
    } catch (e: Exception) {
        handleError(e)
    }
}

suspend fun FirebaseFirestore.update(
    id: String,
    data: HashMap<String, Any?>
): Resource<Boolean> {
    return try {
        this
            .collection(COLLECTION_KEY_NAME)
            .document(id)
            .update(data)
            .await()
        Resource.success(true)
    } catch (e: Exception) {
        handleError(e)
    }
}


suspend fun FirebaseFirestore.delete(
    id: String,
): Resource<Boolean> {
    return try {
        this
            .collection(COLLECTION_KEY_NAME)
            .document(id)
            .delete()
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
            .collection(COLLECTION_KEY_NAME)
            .get()
            .await()
        Resource.success(data.documents)
    } catch (e: Exception) {
        handleError(e)
    }
}