package com.example.mealzeeapp.ui.meals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzeeapp.model.MealsRepository
import com.example.mealzeeapp.model.response.MealResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoryViewModel(
    private val repository: MealsRepository = MealsRepository.getInstance()
) : ViewModel() {

    // job is used as a handle to coroutine for awaiting canceling a coroutine.
    //private val mealsJob = Job()

    init {
        // build our own scope
        //val scope = CoroutineScope(mealsJob + Dispatchers.IO)

        // with viewModeScope you don't need job handlers
        viewModelScope.launch {
            val mealsFromApi = getMeals()
            mealsState.value = mealsFromApi
        }
    }

    val mealsState = mutableStateOf<List<MealResponse>>(emptyList())

//    override fun onCleared() {
//        super.onCleared()
//        mealsJob.cancel()
//    }

    suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }


}