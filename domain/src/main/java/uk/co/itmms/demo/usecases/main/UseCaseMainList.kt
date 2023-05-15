package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import uk.co.itmms.demo.entities.Note
import uk.co.itmms.demo.entities.Todo
import uk.co.itmms.demo.failures.FailureMain
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.IRepositoryNote
import uk.co.itmms.demo.repositories.IRepositoryTodo
import uk.co.itmms.demo.usecases.NoParams
import uk.co.itmms.demo.usecases.UseCaseBase

class UseCaseMainList(
    repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
    private val repositoryTodo: IRepositoryTodo,
    private val repositoryNote: IRepositoryNote,
) : UseCaseBase<NoParams, UseCaseMainList.Result, FailureMain>(
    repositoryDevelopmentLogger, repositoryDevelopmentAnalytics,
) {

    data class Result(
        val todoList: List<Todo>,
        val noteList: List<Note>,
    )

    data class Runtime(
        val todoList: List<Todo> = emptyList(),
        val noteList: List<Note> = emptyList(),
    )

    override suspend fun run(params: NoParams): Either<FailureMain, Result> {
        return Runtime().right()
            .flatMap { runtime -> getTodoList(runtime) }
            .flatMap { runtime -> getNoteList(runtime) }
            .flatMap { runtime -> Result(
                todoList = runtime.todoList,
                noteList = runtime.noteList
            ).right() }
    }

    private suspend fun getTodoList(runtime: Runtime): Either<FailureMain, Runtime> =
        runtime.copy(todoList = repositoryTodo.list()).right()

    private suspend fun getNoteList(runtime: Runtime): Either<FailureMain, Runtime> =
        repositoryNote.list().fold({
            FailureMain.NoteError.left()
        }){
            runtime.copy(noteList = it).right()
        }
}