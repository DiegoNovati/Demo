package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.right
import uk.co.itmms.demo.entities.Todo
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.IRepositoryTodo

class UseCaseMainListTodo(
    repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
    private val repositoryTodo: IRepositoryTodo,
) : UseCaseBase<NoParams, UseCaseMainListTodo.Result, MainFailure>(
    repositoryDevelopmentLogger = repositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
) {
    data class Result(
        val todoList: List<Todo>,
    )

    override suspend fun run(params: NoParams): Either<MainFailure, Result> =
        Result(
            todoList = repositoryTodo.list(),
        ).right()
}