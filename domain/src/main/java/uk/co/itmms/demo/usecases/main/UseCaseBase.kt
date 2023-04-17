package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentAnalytics
import uk.co.itmms.demo.repositories.IRepositoryDevelopmentLogger

object NoParams

abstract class UseCaseBase<Params, Result, Failure>(
    private val repositoryDevelopmentLogger: IRepositoryDevelopmentLogger,
    private val repositoryDevelopmentAnalytics: IRepositoryDevelopmentAnalytics,
) where Params: Any, Result: Any, Failure: Any {

    abstract suspend fun run(params: Params): Either<Failure, Result>

    fun invoke(params: Params, scope: CoroutineScope, onResult: (result: Either<Failure, Result>) -> Unit) {
        scope.launch(Dispatchers.IO) {
            val startMillis = System.currentTimeMillis()
            try {
                val result = run(params)
                analyticsUseCase(getExecutionTime(startMillis))
                scope.launch(Dispatchers.Main) {
                    onResult(result)
                }
            } catch (e: Throwable) {
                logUnexpectedError(params, getExecutionTime(startMillis), e)
                scope.launch(Dispatchers.Main) {
                    //onResult(Either.left(MainFailure.UnexpectedError))
                }
            }
        }
    }

    private fun analyticsUseCase(executionTime: Long) {
        repositoryDevelopmentAnalytics.logUseCase(javaClass.simpleName, executionTime)
    }

    private fun logUnexpectedError(params: Params, executionTime: Long, e: Throwable) {
        repositoryDevelopmentLogger.logIssue(
            javaClass.simpleName,
            createJson(
                params = params.toString(),
                millsec = executionTime,
                error = e.stackTraceToString(),
            ))
    }

    private fun createJson(params: String, millsec: Long, error: String): String {
        return """
            {
                "params": "$params",
                "millsec": "$millsec",
                "error": "$error"
            }
        """.trimIndent()
    }

    private fun getExecutionTime(startMillis: Long): Long {
        return System.currentTimeMillis() - startMillis
    }
}