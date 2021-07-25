package com.moricode.todoapp

import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.feature.todo.data.FirebaseDataSource
import com.moricode.todoapp.feature.todo.data.TodoRepositoryImpl
import com.moricode.todoapp.feature.todo.domain.NetworkDataSource
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.*

class TodoRepositoryTest {

    @Mock
    private lateinit var dataSource: NetworkDataSource

    lateinit var repository: TodoRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = TodoRepositoryImpl(dataSource)
    }

    @Test
    fun showSuccessWhenDataSuccessfullyFetched() {
        runBlocking {
            //Arrange
            val data = hashMapOf<String, Any?>()
            val name = ""
            val todo = TodoEntity()
            //Act
            Mockito.`when`(dataSource.create(name, data)).thenReturn(
                Resource.success(true)
            )
            //Assert
            val result = repository.createTodo(todo)
            assert(result.status == Resource.success(true))
        }
    }
}