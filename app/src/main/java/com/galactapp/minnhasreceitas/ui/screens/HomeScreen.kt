package com.galactapp.minnhasreceitas.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.galactapp.minnhasreceitas.ui.components.ErrorComponent
import com.galactapp.minnhasreceitas.ui.components.LoadingComponent
import com.galactapp.minnhasreceitas.ui.components.SuccessComponent
import com.galactapp.minnhasreceitas.ui.viewmodel.RecipeViewIntent
import com.galactapp.minnhasreceitas.ui.viewmodel.RecipeViewModel
import com.galactapp.minnhasreceitas.ui.viewmodel.RecipeViewState

@Composable
fun HomeScreen(recipeViewModel: RecipeViewModel) {
    val state by recipeViewModel.state


    /// recebe o stato adualizado e exibe o componentre conforme o estado
    when(state) {
        is RecipeViewState.Loading -> LoadingComponent()
        is RecipeViewState.Success -> {
            val recipes = (state as RecipeViewState.Success).recipes
            SuccessComponent(recipes = recipes, onSearchClicked = {query ->
                recipeViewModel.processIntent(RecipeViewIntent.SearchRecipes(query))
            })
        }
        is RecipeViewState.Error -> {
            val message = (state as RecipeViewState.Error).message
            ErrorComponent(message = message, onRefreshClicked = {
                recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
            })
        }
    }

    //
    LaunchedEffect(Unit) {
        recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
    }
}