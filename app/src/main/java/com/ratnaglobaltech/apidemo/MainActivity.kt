package com.ratnaglobaltech.apidemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ratnaglobaltech.apidemo.ui.theme.ApiDemoTheme
import com.ratnaglobaltech.models.CallViweModel

class MainActivity : ComponentActivity() {
    private val viewModel: CallViweModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
       // val vm = CallViweModel()
        super.onCreate(savedInstanceState)
        setContent {
            ApiDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppUI(viewModel = viewModel)
                }
            }
        }
    }


}

@Composable
fun AppUI(viewModel: CallViweModel) {

    LaunchedEffect(Unit) {
        val params = hashMapOf("emailId" to "chandan.singh@ratnaglobaltech.com", "username" to "Chandan Singh","message" to "This is test message") // Replace with your actual parameters
        viewModel.fetchTodos(params)
    }
    val demoModel by viewModel.demoModel.observeAsState(initial = null)
    val error by viewModel.error.observeAsState(initial = null)
    when {
        demoModel != null -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Let's assume the DemoModel has a list of items you want to display
                items(demoModel!!.notifications) { item ->
                    Column{
                        Text(
                            text = item.message,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Divider()
                        Text(
                            text = item.email,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Divider()
                        Text(
                            text = item.notificationSentOn,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                }
            }
        }
        error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = error.toString(), color = Color.Red)
            }
        }
    }


    
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiDemoTheme {
        Greeting("Android")
    }
}