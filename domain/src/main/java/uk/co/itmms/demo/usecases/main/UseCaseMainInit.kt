package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import uk.co.itmms.demo.failures.FailureMain
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger
import uk.co.itmms.demo.repositories.IRepositorySystemInfo
import uk.co.itmms.demo.usecases.NoParams
import uk.co.itmms.demo.usecases.UseCaseBase

class UseCaseMainInit(
    repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
    private val repositorySystemInfo: IRepositorySystemInfo,
) : UseCaseBase<NoParams, Unit, FailureMain>(
    repositoryDevelopmentLogger = repositoryDevelopmentLogger,
    repositoryDevelopmentAnalytics = repositoryDevelopmentAnalytics,
) {

    override suspend fun run(params: NoParams): Either<FailureMain, Unit> {
        val ram = repositorySystemInfo.getSystemInfo().ram
        return if (ram < 4096) {
            FailureMain.NotEnoughMemory.left()
        } else if (ram < 6000) {
            FailureMain.BarelyEnoughMemory.left()
        } else {
            Unit.right()
        }
    }
}