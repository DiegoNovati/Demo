package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.right
import uk.co.itmms.demo.entities.Todo
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.IRepositoryTodo

class UseCaseMainSave(
    repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
    private val repositoryTodo: IRepositoryTodo,
) : UseCaseBase<UseCaseMainSave.Params, Unit, MainFailure>(
    repositoryDevelopmentLogger = repositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
) {
    data class Params(
        val todo: Todo,
    )

    override suspend fun run(params: Params): Either<MainFailure, Unit> {
        repositoryTodo.save(params.todo)
        return Unit.right()
    }
}