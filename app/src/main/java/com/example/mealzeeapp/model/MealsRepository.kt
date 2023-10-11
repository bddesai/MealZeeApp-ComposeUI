package com.example.mealzeeapp.model

import com.example.mealzeeapp.model.api.MealsWebService
import com.example.mealzeeapp.model.response.MealCategoryReponse
import com.example.mealzeeapp.model.response.MealResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealResponse>()

    suspend fun getMeals(): MealCategoryReponse {

        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id: String): MealResponse? {
        return cachedMeals.firstOrNull { it.id == id }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        // double checked locking singleton
        fun getInstance() = instance ?: synchronized(this) {
            // if the instance is null, return MealRepository object by also setting the instance
            instance ?: MealsRepository().also { instance = it }
        }
    }

}