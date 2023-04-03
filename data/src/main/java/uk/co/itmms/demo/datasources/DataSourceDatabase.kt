package uk.co.itmms.demo.datasources

import uk.co.itmms.demo.datasources.database.DbTodo
import uk.co.itmms.demo.datasources.database.IDaoTodo
import uk.co.itmms.demo.datasources.database.IDatabaseApp

interface IDataSourceDatabase {
    fun getDataSourceDatabaseTodo(): IDataSourceDatabaseTodo
}

interface IDataSourceDatabaseTodo {
    suspend fun list(): List<DbTodo>
    suspend fun get(id: Int): DbTodo?
    suspend fun save(todo: DbTodo)
    suspend fun delete(todo: DbTodo)
}

class DataSourceDatabase(
    private val databaseApp: IDatabaseApp,
) : IDataSourceDatabase {
    override fun getDataSourceDatabaseTodo(): IDataSourceDatabaseTodo =
        DataSourceDatabaseTodo(daoTodo = databaseApp.getDaoTodo())
}

data class DataSourceDatabaseTodo(
    private val daoTodo: IDaoTodo,
) : IDataSourceDatabaseTodo {
    override suspend fun list(): List<DbTodo> =
        daoTodo.list()

    override suspend fun get(id: Int): DbTodo? =
        daoTodo.get(id)

    override suspend fun save(todo: DbTodo) =
        daoTodo.save(todo)

    override suspend fun delete(todo: DbTodo) =
        daoTodo.delete(todo)
}