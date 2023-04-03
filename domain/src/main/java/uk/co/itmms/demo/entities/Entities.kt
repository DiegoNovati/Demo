package uk.co.itmms.demo.entities

data class Todo(
    val id: Int = 0,
    val content: String = "",
    val completed: Boolean = false,
)