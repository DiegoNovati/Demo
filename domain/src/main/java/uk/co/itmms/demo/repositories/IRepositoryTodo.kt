package uk.co.itmms.demo.repositories

import uk.co.itmms.demo.entities.Todo

interface IRepositoryTodo {
    suspend fun list(): List<Todo>
    suspend fun get(id: Int): Todo?
    suspend fun save(todo: Todo)
    suspend fun delete(todo: Todo)
}