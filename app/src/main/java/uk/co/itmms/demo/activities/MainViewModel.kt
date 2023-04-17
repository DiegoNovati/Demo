package uk.co.itmms.demo.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.co.itmms.demo.entities.Todo
import uk.co.itmms.demo.usecases.main.NoParams
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
        useCaseMainInit.invoke(NoParams, viewModelScope) {
            it.fold({ failure ->
                println("********** useCaseMainInit: Failure = $failure")
            }){
                println("********** useCaseMainInit: Success")
            }
        }

    }

    fun loadDatabase() {
        useCaseMainListTodo.invoke(NoParams, viewModelScope) {
            it.fold({ failure ->
                println("********** useCaseMainListTodo: Failure = $failure")
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
                println("********** useCaseMainSave: Failure = $failure")
            }){
                println("********** useCaseMainSave: Success")
            }
        }
    }
}