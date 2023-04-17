package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.IRepositorySystemInfo

sealed interface MainFailure {
    object NotEnoughMemory : MainFailure
    object BarelyEnoughMemory : MainFailure
}

class UseCaseMainInit(
    repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
    private val repositorySystemInfo: IRepositorySystemInfo,
) : UseCaseBase<NoParams, Unit, MainFailure>(
    repositoryDevelopmentLogger = repositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
) {

    override suspend fun run(params: NoParams): Either<MainFailure, Unit> {
        val ram = repositorySystemInfo.getSystemInfo().ram
        return if (ram < 4096) {
            MainFailure.NotEnoughMemory.left()
        } else if (ram < 6000) {
            MainFailure.BarelyEnoughMemory.left()
        } else {
            Unit.right()
        }
    }
}