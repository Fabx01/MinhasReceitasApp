package com.galactapp.minnhasreceitas.data.network

import com.galactapp.minnhasreceitas.data.model.Meal
import com.galactapp.minnhasreceitas.data.model.MealResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Define a interface que representa a API
interface MealApiService {
    @GET("random.php")
    suspend fun getRandomRecipe(): MealResponse

    @GET("search.php")
    suspend fun getSearchedRecipe(@Query("s") query: String): MealResponse
}

object MealApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(MealApiService::class.java)

    suspend fun getRandomRecipe(): List<Meal> {
        val response = apiService.getRandomRecipe()
        return response.meals
    }

    suspend fun getSearchedRecipe(query: String): List<Meal> {
        val response = apiService.getSearchedRecipe(query)
        return response.meals
    }
}
