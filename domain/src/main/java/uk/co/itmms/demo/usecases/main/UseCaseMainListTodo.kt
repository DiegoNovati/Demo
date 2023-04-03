package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.right
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.co.itmms.demo.entities.Todo
import uk.co.itmms.demo.repositories.IRepositoryTodo

class UseCaseMainListTodo(
    private val repositoryTodo: IRepositoryTodo,
) {
    data class Result(
        val todoList: List<Todo>,
    )

    fun invoke(scope: CoroutineScope, onResult: (result: Either<MainFailure, Result>) -> Unit) {
        scope.launch(Dispatchers.IO) {
            val result = Result(
                todoList = repositoryTodo.list(),
            )
            scope.launch(Dispatchers.Main) {
                onResult(result.right())
            }
        }
    }
}