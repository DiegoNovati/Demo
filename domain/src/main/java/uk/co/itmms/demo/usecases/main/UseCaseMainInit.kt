package uk.co.itmms.demo.usecases.main

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.co.itmms.demo.repositories.IRepositorySystemInfo

sealed interface MainFailure {
    object NotEnoughMemory : MainFailure
    object BarelyEnoughMemory : MainFailure
}

class UseCaseMainInit(
    private val repositorySystemInfo: IRepositorySystemInfo,
) {

    fun invoke(scope: CoroutineScope, onResult: (result: Either<MainFailure, Unit>) -> Unit) {
        scope.launch(Dispatchers.IO) {
            val ram = repositorySystemInfo.getSystemInfo().ram

            println("********** ran = $ram")

            scope.launch(Dispatchers.Main) {
                if (ram < 4096) {
                    onResult(MainFailure.NotEnoughMemory.left())
                } else if (ram < 6000) {
                    onResult(MainFailure.BarelyEnoughMemory.left())
                } else {
                    onResult(Unit.right())
                }
            }
        }
    }
}