package com.blackpaws.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blackpaws.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first_screen") {

        composable("first_screen") {
            FirstScreen(onNavigateToSecondScreen = { name ->
                navController.navigate("second_screen/$name") })
        }

        composable("second_screen/{name}") {
            val name = it.arguments?.getString("name") ?: "no name"
            SecondScreen(
                name = name,
                onNavigateToThirdScreen = {
                    nextName -> navController.navigate("third_screen/$nextName")
                })
        }

        composable("third_screen/{name}") {
            val name = it.arguments?.getString("name") ?: "no name"
            ThirdScreen(
                name = name,
                onNavigateToFirstScreen = {navController.navigate("first_screen")})
        }


//        composable("first_screen") {
//            FirstScreen {
//                navController.navigate("second_screen")
//            }
//        }
//        composable("second_screen") {
//            SecondScreen {
//                navController.navigate("first_screen")
//            }
//        }
////        In this code snippet, the composable function "FirstScreen" is directly accessed, and
////        within its body, the navigation to the second screen is called using
////        navController.navigate("second_screen"). The navigation action is directly executed
////        when the composable function is invoked.

//        composable("first_screen") {
//            FirstScreen(onNavigateToSecondScreen = { navController.navigate("second_screen") })
//        }
//        composable("second_screen") {
//            FirstScreen(onNavigateToSecondScreen = { navController.navigate("first_screen") })
//        }
////        In this code snippet, the composable function "FirstScreen" is called with the parameter
////        onNavigateToSecondScreen, which is a lambda function that performs the navigation action.
////        The navigation action is not directly executed within the composable function, but rather
////        it is passed as a callback function to the "FirstScreen" composable.
////
////        The advantage of the second approach is that it allows for more flexibility and separation
////        of concerns. By passing the navigation action as a parameter, the composable
////        function "FirstScreen" can be more reusable and doesn't need to directly depend on the
////        navigation logic. It can receive navigation behavior from an external source, which can be
////        useful in testing or adapting the behavior based on different scenarios.
    }
}