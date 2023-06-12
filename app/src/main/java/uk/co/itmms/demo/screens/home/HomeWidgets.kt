package uk.co.itmms.demo.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.itmms.demo.ui.theme.DemoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoInput(
    todo: String,
    onChange: (String) -> Unit,
    onAdd: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            label = {
                Text("Todo")
            },
            value = todo,
            onValueChange = { newTodo -> onChange(newTodo) },
        )
        Button(
            onClick = onAdd,
        ) {
            Text("Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoInputEmptyPreview() {
    DemoTheme {
        TodoInput(
            todo = "",
            onChange = {},
            onAdd = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoInputPreview() {
    DemoTheme {
        TodoInput(
            todo = "My next todo",
            onChange = {},
            onAdd = {},
        )
    }
}