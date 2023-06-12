package uk.co.itmms.demo.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import uk.co.itmms.demo.screens.home.ScreenHome
import uk.co.itmms.demo.ui.theme.DemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.loadDatabase()

        setContent {
            DemoTheme {
                ScreenHome()
            }
//            DemoTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android", onSave = { mainViewModel.saveDatabase() })
//                }
//            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    onSave: () -> Unit = {},
) {
    Column {
        Text(
            text = "Hello $name!",
        )
        Button(onClick = onSave) {
            Text(text = "Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoTheme {
        Greeting("Android")
    }
}