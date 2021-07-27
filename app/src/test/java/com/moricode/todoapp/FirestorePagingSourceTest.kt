package com.moricode.todoapp

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.feature.todo.data.COLLECTION_KEY_NAME
import com.moricode.todoapp.feature.todo.data.FirestorePagingSource
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.presentation.list.PAGE_SIZE
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Before
import org.junit.Test

class FirestorePagingSourceTest {

    private lateinit var pagingSource: FirestorePagingSource

    @MockK
    private lateinit var collectionRef: CollectionReference

    @MockK
    private lateinit var documentRef: DocumentReference

    @MockK
    private lateinit var documentSnapshot: DocumentSnapshot

    @MockK
    private lateinit var db: FirebaseFirestore

    private val mockTodoList = listOf(
        TodoEntity(),
        TodoEntity(),
        TodoEntity()
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        pagingSource = FirestorePagingSource(
            db,
            COLLECTION_KEY_NAME,
            PAGE_SIZE
        )
    }

    @Test
    fun testSuccessResults() {
        runBlocking {
            val data = hashMapOf<String, Any?>(
                "test" to "testing"
            )
            every { db.collection("todo") } returns collectionRef
            every { collectionRef.document("test") } returns documentRef
            coEvery { documentRef.get().await() } returns documentSnapshot
            every { documentSnapshot.data } returns data

            assertEquals(
                PagingSource.LoadResult.Page(
                    data = listOf(mockTodoList[0], mockTodoList[1]),
                    prevKey = mockTodoList[0].id,
                    nextKey = mockTodoList[1].id
                ),
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                ),
            )
        }
    }
}