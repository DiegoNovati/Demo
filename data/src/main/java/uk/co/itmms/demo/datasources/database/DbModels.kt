package uk.co.itmms.demo.datasources.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import uk.co.itmms.demo.entities.Todo

@Entity(
    tableName = "todo",
)
data class DbTodo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val content: String = "",
    val completed: Boolean = false,
)

fun DbTodo.toTodo(): Todo =
    Todo(
        id = id,
        content = content,
        completed = completed,
    )

fun List<DbTodo>.toTodoList(): List<Todo> =
    map { it.toTodo() }

fun Todo.toDbTodo(): DbTodo =
    DbTodo(
        id = id,
        content = content,
        completed = completed,
    )

fun List<Todo>.toDbTodoList(): List<DbTodo> =
    map { it.toDbTodo() }