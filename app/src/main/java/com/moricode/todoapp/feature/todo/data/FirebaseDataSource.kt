package com.moricode.todoapp.feature.todo.data

import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.core.base.handleError
import com.moricode.todoapp.feature.todo.domain.NetworkDataSource
import kotlinx.coroutines.tasks.await

const val COLLECTION_KEY_NAME = "todo"

class FirebaseDataSource(
    private val db: FirebaseFirestore
) : NetworkDataSource {

    override suspend fun create(
        childName: String,
        data: HashMap<String, Any?>
    ): Resource<Boolean> {
        return try {
            db
                .collection(COLLECTION_KEY_NAME)
                .document(childName)
                .set(data)
                .await()
            Resource.success(true)
        } catch (e: Exception) {
            handleError(e)
        }
    }

    override suspend fun update(
        id: String,
        data: HashMap<String, Any?>
    ): Resource<Boolean> {
        return try {
            db
                .collection(COLLECTION_KEY_NAME)
                .document(id)
                .update(data)
                .await()
            Resource.success(true)
        } catch (e: Exception) {
            handleError(e)
        }
    }


    override suspend fun delete(
        id: String,
    ): Resource<Boolean> {
        return try {
            db
                .collection(COLLECTION_KEY_NAME)
                .document(id)
                .delete()
                .await()
            Resource.success(true)
        } catch (e: Exception) {
            handleError(e)
        }
    }
}