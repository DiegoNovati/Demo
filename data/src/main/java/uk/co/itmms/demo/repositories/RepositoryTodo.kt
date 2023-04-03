package uk.co.itmms.demo.repositories

import uk.co.itmms.demo.datasources.IDataSourceDatabase
import uk.co.itmms.demo.datasources.database.toDbTodo
import uk.co.itmms.demo.datasources.database.toTodo
import uk.co.itmms.demo.datasources.database.toTodoList
import uk.co.itmms.demo.entities.Todo

class RepositoryTodo(
    private val dataSourceDatabase: IDataSourceDatabase,
) : IRepositoryTodo {
    override suspend fun list(): List<Todo> =
        dataSourceDatabase.getDataSourceDatabaseTodo().list().toTodoList()

    override suspend fun get(id: Int): Todo? =
        dataSourceDatabase.getDataSourceDatabaseTodo().get(id)?.toTodo()

    override suspend fun save(todo: Todo) =
        dataSourceDatabase.getDataSourceDatabaseTodo().save(todo.toDbTodo())

    override suspend fun delete(todo: Todo) =
        dataSourceDatabase.getDataSourceDatabaseTodo().delete(todo.toDbTodo())
}