package uk.co.itmms.demo.repositories

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import uk.co.itmms.demo.BaseDataTest
import uk.co.itmms.demo.datasources.IDataSourceDatabase
import uk.co.itmms.demo.datasources.IDataSourceDatabaseTodo
import uk.co.itmms.demo.datasources.database.DbTodo
import uk.co.itmms.demo.datasources.database.toTodo
import uk.co.itmms.demo.datasources.database.toTodoList

class RepositoryTodoTest : BaseDataTest() {

    @MockK
    private lateinit var mockDataSourceDatabaseTodo: IDataSourceDatabaseTodo

    @MockK
    private lateinit var mockDataSourceDatabase: IDataSourceDatabase

    @InjectMockKs
    private lateinit var repositoryTodo: RepositoryTodo

    @Before
    fun setup() {
        coEvery { mockDataSourceDatabase.getDataSourceDatabaseTodo() } returns mockDataSourceDatabaseTodo
    }

    @Test
    fun `testing list`() = runBlocking {
        val list = listOf(DbTodo())

        coEvery { mockDataSourceDatabaseTodo.list() } returns list

        val actual = repositoryTodo.list()

        assertEquals(list.toTodoList(), actual)

        coVerify(exactly = 1) {
            mockDataSourceDatabaseTodo.list()
        }
        confirmVerified(mockDataSourceDatabaseTodo)
    }

    @Test
    fun `testing get`() = runBlocking {
        val id = 1
        val todo = DbTodo()

        coEvery { mockDataSourceDatabaseTodo.get(id) } returns todo

        val actual = repositoryTodo.get(id)

        assertEquals(todo.toTodo(), actual)

        coVerify(exactly = 1) {
            mockDataSourceDatabaseTodo.get(id)
        }
        confirmVerified(mockDataSourceDatabaseTodo)
    }

    @Test
    fun `testing save`() = runBlocking {
        val todo = DbTodo()

        repositoryTodo.save(todo.toTodo())

        coVerify(exactly = 1) {
            mockDataSourceDatabaseTodo.save(todo)
        }
        confirmVerified(mockDataSourceDatabaseTodo)
    }

    @Test
    fun `testing delete`() = runBlocking {
        val todo = DbTodo()

        repositoryTodo.delete(todo.toTodo())

        coVerify(exactly = 1) {
            mockDataSourceDatabaseTodo.delete(todo)
        }
        confirmVerified(mockDataSourceDatabaseTodo)
    }
}