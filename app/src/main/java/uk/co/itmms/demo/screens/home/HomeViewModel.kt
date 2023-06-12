package uk.co.itmms.demo.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    data class State(
        val todo: String = "",
        val todoList: List<String> = emptyList(),
    )

    private val _state: MutableLiveData<State> = MutableLiveData(State())
    val state: LiveData<State> = _state

    sealed interface EventType {
        data class UpdateTodo(val todo: String) : EventType
        data class UpdateState(val state: State) : EventType
        object Add : EventType
    }

    fun onEvent(event: EventType) {
        when (event) {
            is EventType.UpdateTodo -> _state.value = _state.value?.copy(todo = event.todo)
            is EventType.UpdateState -> { _state.value = event.state }
            EventType.Add -> {
               _state.value = _state.value?.copy(
                   todoList = _state.value!!.todoList + state.value!!.todo,
                   todo = "",
               )
            }
        }
    }
}