package uk.co.itmms.demo.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.co.itmms.demo.entities.Todo
import uk.co.itmms.demo.failures.FailureMain
import uk.co.itmms.demo.failures.UnexpectedError
import uk.co.itmms.demo.usecases.NoParams
import uk.co.itmms.demo.usecases.main.UseCaseMainInit
import uk.co.itmms.demo.usecases.main.UseCaseMainListTodo
import uk.co.itmms.demo.usecases.main.UseCaseMainSave
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    useCaseMainInit: UseCaseMainInit,
    private val useCaseMainListTodo: UseCaseMainListTodo,
    private val useCaseMainSave: UseCaseMainSave,
): ViewModel() {
    init {
        // display the progress loader
        useCaseMainInit.invoke(NoParams, viewModelScope) {
            // hide the progress loader
            it.fold({ failure ->
                showFailure("useCaseMainInit", failure)
            }){
                println("********** useCaseMainInit: Success")
            }
        }

    }

    fun loadDatabase() {
        useCaseMainListTodo.invoke(NoParams, viewModelScope) {
            it.fold({ failure ->
                showFailure("useCaseMainListTodo", failure)
            }){ result ->
                println("********** useCaseMainListTodo: Success = $result")
            }
        }
    }

    fun saveDatabase() {
        val params = UseCaseMainSave.Params(
            todo = Todo(
                id = 0,
                content = "Contenuto",
                completed = false,
            )
        )
        useCaseMainSave.invoke(params, viewModelScope) {
            it.fold({ failure ->
                showFailure("useCaseMainSave", failure)
            }){
                println("********** useCaseMainSave: Success")
            }
        }
    }

    private fun showFailure(useCaseName: String, failure: FailureMain) {
        when (failure) {
            FailureMain.BarelyEnoughMemory -> println("********** $useCaseName: Failure = BarelyEnoughMemory")
            FailureMain.NotEnoughMemory -> println("********** $useCaseName: Failure = NotEnoughMemory")
            is UnexpectedError -> println("********** $useCaseName: Failure = ${failure.e}")
        }
    }
}