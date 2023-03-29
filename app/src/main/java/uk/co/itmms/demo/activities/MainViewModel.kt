package uk.co.itmms.demo.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uk.co.itmms.demo.DataInterface

class MainViewModel : ViewModel() {

    private val useCaseMainInit = DataInterface.getUseCaseMainInit()

    init {
        useCaseMainInit.invoke(viewModelScope) {
            it.fold({ failure ->
                println("********** useCaseMainInit: Failure = $failure")
            }){
                println("********** useCaseMainInit: Success")
            }
        }
    }
}