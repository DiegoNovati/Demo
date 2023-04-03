package uk.co.itmms.demo.datasources

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import uk.co.itmms.demo.BaseDataTest
import uk.co.itmms.demo.datasources.database.IDaoTodo
import uk.co.itmms.demo.datasources.database.IDatabaseApp

class DataSourceDatabaseTest : BaseDataTest() {

    @MockK
    private lateinit var mockDatabaseApp: IDatabaseApp

    @InjectMockKs
    private lateinit var dataSourceDatabase: DataSourceDatabase

    @Test
    fun `testing getDataSourceDatabaseTodo`() = runBlocking {
        val mockDaoTodo = mockk<IDaoTodo>()

        coEvery { mockDatabaseApp.getDaoTodo() } returns mockDaoTodo

        val actual = dataSourceDatabase.getDataSourceDatabaseTodo()

        assertEquals(DataSourceDatabaseTodo(daoTodo = mockDaoTodo), actual)

        coVerify(exactly = 1) {
            mockDatabaseApp.getDaoTodo()
        }
        confirmVerified(mockDatabaseApp)
    }
}