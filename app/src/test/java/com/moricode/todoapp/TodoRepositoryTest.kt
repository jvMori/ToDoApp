package com.moricode.todoapp

import com.google.firebase.firestore.*
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.feature.todo.data.FirebaseDataSource
import com.moricode.todoapp.feature.todo.data.TodoRepositoryImpl
import com.moricode.todoapp.feature.todo.domain.NetworkDataSource
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Before
import org.junit.Test

class TodoRepositoryTest {

    @MockK
    private lateinit var dataSource: NetworkDataSource

    @MockK
    private lateinit var collectionRef : CollectionReference

    @MockK
    private lateinit var documentRef : DocumentReference

    @MockK
    private lateinit var documentSnapshot : DocumentSnapshot

    @MockK
    private lateinit var db: FirebaseFirestore

    lateinit var repository: TodoRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        dataSource = spyk(FirebaseDataSource(db))
        repository = TodoRepositoryImpl(dataSource)
    }

    @Test
    fun showSuccessWhenDataSuccessfullyFetched() {
        runBlocking {
            //Arrange
            val data = hashMapOf<String, Any?>(
                "test" to "testing"
            )
            val name = "test"
            val todo = TodoEntity()
            every { db.collection("todo") } returns collectionRef
            every { collectionRef.document(name) } returns documentRef
            coEvery { documentRef.set(data).await() } returns Void.TYPE.newInstance()
            every { documentSnapshot.data } returns data
            coEvery { dataSource.create(name, data) } returns Resource.success(true)

            //Act
            val result = repository.createTodo(todo)

            //Assert
            assert(result.status == Resource.success(true))
        }
    }
}