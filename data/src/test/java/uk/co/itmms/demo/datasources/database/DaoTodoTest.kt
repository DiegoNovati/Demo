package uk.co.itmms.demo.datasources.database

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import uk.co.itmms.demo.BaseDataDaoTest

class DaoTodoTest : BaseDataDaoTest() {

    @Test
    fun `test daoTodo`() = runBlocking {
        // Save (insert)/list
        var todo = DbTodo(
            content = "Test",
            completed = false,
        )

        daoTodo.save(todo)
        assertEquals(1, daoTodo.list().size)

        todo = daoTodo.list()[0]

        // Update
        daoTodo.save(todo.copy(completed = true))
        todo = daoTodo.list()[0]
        assertTrue(todo.completed)

        // Get
        assertNotNull(daoTodo.get(todo.id))
        assertNull(daoTodo.get(9999))

        // Delete
        daoTodo.delete(todo)
        assertEquals(0, daoTodo.list().size)
    }
}