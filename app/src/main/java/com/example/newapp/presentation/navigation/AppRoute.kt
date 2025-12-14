package com.example.newapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newapp.dataLayer.models.Character
import com.example.newapp.presentation.Details.DetailScreen
import com.example.newapp.presentation.home.HomeScreen
import com.example.newapp.presentation.home.HomeScreen2
import com.example.newapp.presentation.stepCounterView.StepCounterScreen

enum class AppRoute{ HomeFlow,HomeLiveData , StepCounter }
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(modifier = modifier,
        navController = navController,
        startDestination = AppRoute.HomeLiveData.name
    ){

        composable(route = AppRoute.StepCounter.name) {
            StepCounterScreen()
        }
        composable(AppRoute.HomeLiveData.name) {
            HomeScreen2{ character ->
                navController.navigate(route= character)
            }
        }
        composable(AppRoute.HomeFlow.name) {
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