package com.daanidev.newcomposenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.daanidev.newcomposenavigation.ui.screens.ScreenA
import com.daanidev.newcomposenavigation.ui.screens.ScreenB
import com.daanidev.newcomposenavigation.ui.theme.NewComposeNavigationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NewComposeNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = ScreenARoute) {
                        composable<ScreenARoute> {
                            ScreenA(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {
                                navController.navigate(ScreenBRoute("Muhammad", "Danish", null))
                            }
                        }

                        composable<ScreenBRoute> {
                            ScreenB(screenBRoute = it.toRoute()) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenARoute

@Serializable
data class ScreenBRoute(val firstName: String, val lastName: String, val gender: String?)



