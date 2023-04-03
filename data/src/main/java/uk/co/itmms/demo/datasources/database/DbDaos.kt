package uk.co.itmms.demo.datasources.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface IDaoTodo {
    @Query("SELECT * FROM todo")
    suspend fun list(): List<DbTodo>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun get(id: Int): DbTodo?

    @Upsert
    suspend fun save(todo: DbTodo)

    @Delete
    suspend fun delete(todo: DbTodo)
}