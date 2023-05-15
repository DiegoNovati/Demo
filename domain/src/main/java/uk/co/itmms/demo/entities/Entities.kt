package uk.co.itmms.demo.entities

import java.util.Date

data class Todo(
    val id: Int = 0,
    val content: String = "",
    val completed: Boolean = false,
)

data class Note(
    val id: Int = 0,
    val note: String = "",
    val dateCreation: Date,
    val dateLastUpdate: Date?,
)