package uk.co.itmms.demo.usecases.home

import arrow.core.Either
import arrow.core.right
import uk.co.itmms.demo.entities.Note
import uk.co.itmms.demo.failures.FailureHome
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.usecases.NoParams
import uk.co.itmms.demo.usecases.UseCaseBase

class UseCaseHomeListNote(
    repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
) : UseCaseBase<NoParams, UseCaseHomeListNote.Result, FailureHome>(
    repositoryDevelopmentLogger, repositoryDevelopmentAnalytics,
) {
    data class Result(
        val notes: List<Note>,
    )

    override suspend fun run(params: NoParams): Either<FailureHome, Result> =
        Result(notes = emptyList()).right()
}