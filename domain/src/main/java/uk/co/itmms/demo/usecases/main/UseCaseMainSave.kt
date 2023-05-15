package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.right
import uk.co.itmms.demo.entities.Todo
import uk.co.itmms.demo.failures.FailureMain
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.IRepositoryTodo
import uk.co.itmms.demo.usecases.UseCaseBase

class UseCaseMainSave(
    repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
    private val repositoryTodo: IRepositoryTodo,
) : UseCaseBase<UseCaseMainSave.Params, Unit, FailureMain>(
    repositoryDevelopmentLogger = repositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
) {
    data class Params(
        val todo: Todo,
    )

    override suspend fun run(params: Params): Either<FailureMain, Unit> {
        repositoryTodo.save(params.todo)
        return Unit.right()
    }
}