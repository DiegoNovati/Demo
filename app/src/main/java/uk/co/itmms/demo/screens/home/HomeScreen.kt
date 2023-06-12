package uk.co.itmms.demo.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uk.co.itmms.demo.ui.theme.DemoTheme

@Composable
fun ScreenHome(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    viewModel.state.observeAsState(initial = HomeViewModel.State()).value.apply {
        ScreenHomeUI(
            state = this,
            onEvent = { viewModel.onEvent(it) }, // viewModel::onEvent,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHomeUI(
    state: HomeViewModel.State,
    onEvent: (HomeViewModel.EventType) -> Unit,
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp),
        ) {
            TodoInput(
                todo = state.todo,
                onChange = { newTodo -> onEvent(HomeViewModel.EventType.UpdateTodo(newTodo)) },
                onAdd = { onEvent(HomeViewModel.EventType.Add) },
            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//            ) {
//                OutlinedTextField(
//                    modifier = Modifier
//                        .weight(1f),
//                    label = {
//                        Text("Todo")
//                    },
//                    value = state.todo,
//                    onValueChange = { newTodo -> onEvent(HomeViewModel.EventType.UpdateTodo(newTodo)) },
//                    //onValueChange = { newTodo -> onEvent(HomeViewModel.EventType.UpdateState(state.copy(todo = newTodo))) },
//                )
//                Button(
//                    onClick = {
//                        onEvent(HomeViewModel.EventType.Add)
//                    },
//                ) {
//                    Text("Add")
//                }
//            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(state.todoList.size) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        text = "- ${state.todoList[it]}",
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenHomeUIEmptyPreview() {
    DemoTheme {
        ScreenHomeUI(
            state = HomeViewModel.State(
                todo = "",
                todoList = listOf(),
            ),
            onEvent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenHomeUIPreview() {
    DemoTheme {
        ScreenHomeUI(
            state = HomeViewModel.State(
                todo = "Inserting a new todo",
                todoList = listOf("Todo 1", "Todo 2"),
            ),
            onEvent = {},
        )
    }
}