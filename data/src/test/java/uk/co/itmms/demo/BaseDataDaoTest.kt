package uk.co.itmms.demo

import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import uk.co.itmms.demo.datasources.database.DbDatabaseApp
import uk.co.itmms.demo.datasources.database.IDaoTodo

abstract class BaseDataDaoTest : BaseDataRobolectricTest() {
    lateinit var daoTodo: IDaoTodo

    private lateinit var databaseApp: DbDatabaseApp

    @Before
    fun createDatabase() {
        databaseApp = Room.inMemoryDatabaseBuilder(context, DbDatabaseApp::class.java).build()

        daoTodo = databaseApp.getDaoTodo()
    }

    @After
    fun destroyDatabase() = runBlocking(Dispatchers.IO) {
        if (this@BaseDataDaoTest::databaseApp.isInitialized) {
            databaseApp.clearAllTables()
            databaseApp.close()
        }
    }
}