package uk.co.itmms.demo.datasources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

interface IDatabaseApp {
    fun getDaoTodo(): IDaoTodo
}

@Database(
    entities = [
        DbTodo::class,
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = [],
)
abstract class DbDatabaseApp : IDatabaseApp, RoomDatabase() {
    abstract override fun getDaoTodo(): IDaoTodo
}

fun openDatabase(applicationContext: Context, dbName: String): IDatabaseApp =
    Room.databaseBuilder(
        applicationContext,
        DbDatabaseApp::class.java,
        dbName,
    ).build()