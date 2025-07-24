package com.example.newapp.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newapp.data.models.Character
import com.example.newapp.view.Details.DetailScreen
import com.example.newapp.view.home.HomeScreen

enum class AppRoute{ Home, }
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(modifier = modifier,
        navController = navController,
        startDestination = AppRoute.Home.name
    ){
        composable(AppRoute.Home.name) {
            HomeScreen{ character ->
                navController.navigate(route= character)
            }
        }
        composable<Character> { backStackEntry->
            val data = backStackEntry.toRoute<Character>()
            DetailScreen(data)
        }
    }
}