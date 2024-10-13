package com.example.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffold.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScaffoldApp()
                }
            }
        }
    }
}

@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            Main(navController)
        }
        composable(route = "Info") {
            Info(navController)
        }
        composable(route = "Settings") {
            Settings(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },
        actions = { IconButton(onClick = { expanded = !expanded })
            {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
            {
                DropdownMenuItem(text = { Text("Info") }, onClick = { navController.navigate("info") })
                DropdownMenuItem(text = { Text("Settings") }, onClick = { navController.navigate("settings") })
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController)
{
    TopAppBar(title = { Text(title) }, navigationIcon = { IconButton(onClick = {navController.navigateUp()}) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null) } })
}

@Composable
fun Main(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { MyTopBar("My App", navController) },
        content = { innerPadding -> Column(modifier = modifier.padding(innerPadding)) { Text(text = "Home screen") } },
        )
}

@Composable
fun Info(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) },
        content = { innerPadding ->
            Column(modifier =modifier.padding(innerPadding)) {
                Text(text = "Info screen")
            }
        },
    )
}

@Composable
fun Settings(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController) },
        content = { innerPadding ->
            Column(modifier = modifier.padding(innerPadding)) {
                Text(text = "Settings screen")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldTheme() {
        ScaffoldApp()
    }
}