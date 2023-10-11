package com.example.mealzeeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzeeapp.ui.meal_detail.MealDetailScreen
import com.example.mealzeeapp.ui.meal_detail.MealDetailsViewModel
import com.example.mealzeeapp.ui.meals.MealsCategoryScreen
import com.example.mealzeeapp.ui.theme.MealzeeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MealzeeAppTheme {
                MealzeeApp()
            }
        }
    }
}


@Composable
private fun MealzeeApp() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "destination_meals_list") {
        composable(route = "destination_meals_list") {
            MealsCategoryScreen() { navigationMealId ->
                navController.navigate("destination_meals_details/${navigationMealId}")

            }
        }
        composable(
            route = "destination_meals_details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val viewModel : MealDetailsViewModel = viewModel()
            MealDetailScreen(viewModel.mealState.value)
        }
    }
}

