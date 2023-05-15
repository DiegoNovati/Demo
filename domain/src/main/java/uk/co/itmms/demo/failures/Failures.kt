package uk.co.itmms.demo.failures

//   https://jorgecastillo.dev/sealed-interfaces-kotlin

data class UnexpectedError(val e: Throwable) : BaseFailure()

sealed class BaseFailure: FailureMain, FailureHome

sealed interface IFailure

sealed interface FailureMain : IFailure {
    object NotEnoughMemory : FailureMain
    object BarelyEnoughMemory : FailureMain
    object NoteError : FailureMain
}

sealed interface FailureHome : IFailure