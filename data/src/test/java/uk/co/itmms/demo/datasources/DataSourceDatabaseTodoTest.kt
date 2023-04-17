package uk.co.itmms.demo.datasources

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import uk.co.itmms.demo.BaseDataTest
import uk.co.itmms.demo.datasources.database.DbTodo
import uk.co.itmms.demo.datasources.database.IDaoTodo

class DataSourceDatabaseTodoTest : BaseDataTest() {

    @MockK
    private lateinit var mockDaoTodo: IDaoTodo

    @InjectMockKs
    private lateinit var dataSourceDatabaseTodo: DataSourceDatabaseTodo

    private val dbTodo = DbTodo()

    @Test
    fun `testing list`() = runBlocking {
        val list = listOf(dbTodo)

        coEvery { mockDaoTodo.list() } returns list

        val actual = dataSourceDatabaseTodo.list()

        assertEquals(list, actual)

        coVerify(exactly = 1) {
            mockDaoTodo.list()
        }
        confirmVerified(mockDaoTodo)
    }

    @Test
    fun `testing get`() = runBlocking {
        val id = 1

        coEvery { mockDaoTodo.get(id) } returns dbTodo

        val actual = dataSourceDatabaseTodo.get(id)

        assertEquals(dbTodo, actual)

        coVerify(exactly = 1) {
            mockDaoTodo.get(id)
        }
        confirmVerified(mockDaoTodo)
    }

    @Test
    fun `testing save`() = runBlocking {
        dataSourceDatabaseTodo.save(dbTodo)

        coVerify(exactly = 1) {
            mockDaoTodo.save(dbTodo)
        }
        confirmVerified(mockDaoTodo)
    }

    @Test
    fun `testing delete`() = runBlocking {
        dataSourceDatabaseTodo.delete(dbTodo)

        coVerify(exactly = 1) {
            mockDaoTodo.delete(dbTodo)
        }
        confirmVerified(mockDaoTodo)
    }
}