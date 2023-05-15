package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.right
import uk.co.itmms.demo.entities.Todo
import uk.co.itmms.demo.failures.FailureMain
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.IRepositoryTodo
import uk.co.itmms.demo.usecases.NoParams
import uk.co.itmms.demo.usecases.UseCaseBase

class UseCaseMainListTodo(
    repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
    private val repositoryTodo: IRepositoryTodo,
) : UseCaseBase<NoParams, UseCaseMainListTodo.Result, FailureMain>(
    repositoryDevelopmentLogger = repositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
) {
    data class Result(
        val todoList: List<Todo>,
    )

    override suspend fun run(params: NoParams): Either<FailureMain, Result> =
        Result(
            todoList = repositoryTodo.list(),
        ).right()
}