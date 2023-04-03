package uk.co.itmms.demo.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.co.itmms.demo.usecases.main.UseCaseMainInit
import uk.co.itmms.demo.usecases.main.UseCaseMainListTodo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    useCaseMainInit: UseCaseMainInit,
    useCaseMainListTodo: UseCaseMainListTodo,
): ViewModel() {
    init {
        useCaseMainInit.invoke(viewModelScope) {
            it.fold({ failure ->
                println("********** useCaseMainInit: Failure = $failure")
            }){
                println("********** useCaseMainInit: Success")
            }
        }
        useCaseMainListTodo.invoke(viewModelScope) {
            it.fold({ failure ->
                println("********** useCaseMainListTodo: Failure = $failure")
            }){ result ->
                println("********** useCaseMainListTodo: Success = $result")
            }
        }
    }

    fun loadDatabase() {
        println("********** loadDatabase")
    }
}