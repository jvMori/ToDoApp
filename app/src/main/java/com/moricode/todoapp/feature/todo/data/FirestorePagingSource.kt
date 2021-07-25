package com.moricode.todoapp.feature.todo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import kotlinx.coroutines.tasks.await


class FirestorePagingSource(
    private val db: FirebaseFirestore,
    private val collection : String,
    private val limit : Long = 30,
) : PagingSource<QuerySnapshot, TodoEntity>() {

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, TodoEntity> {
        return try {

            val currentPage = params.key ?: db.collection(collection)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(limit)
                .get()
                .await()


            val lastDocumentSnapshot = currentPage.documents[currentPage.size() - 1]

            val nextPage = db.collection(collection)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(limit)
                .startAfter(lastDocumentSnapshot)
                .get()
                .await()

            LoadResult.Page(
                data = currentPage.toObjects(TodoEntity::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<QuerySnapshot, TodoEntity>): QuerySnapshot? {
        return null
    }
}