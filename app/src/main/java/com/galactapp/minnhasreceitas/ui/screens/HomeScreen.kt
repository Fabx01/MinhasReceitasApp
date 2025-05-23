package com.galactapp.minnhasreceitas.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.galactapp.minnhasreceitas.ui.components.ErrorComponent
import com.galactapp.minnhasreceitas.ui.components.LoadingComponent
import com.galactapp.minnhasreceitas.ui.components.SuccessComponent
import com.galactapp.minnhasreceitas.ui.theme.MinnhasReceitasTheme

import com.galactapp.minnhasreceitas.ui.viewmodel.RecipeViewIntent
import com.galactapp.minnhasreceitas.ui.viewmodel.RecipeViewModel
import com.galactapp.minnhasreceitas.ui.viewmodel.RecipeViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(recipeViewModel: RecipeViewModel) {
    val state by recipeViewModel.state

    MinnhasReceitasTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Minhas Receitas") })
            }
        ) { paddingValues ->
            // Container for the content
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
                when (state) {
                    is RecipeViewState.Loading -> LoadingComponent()
                    is RecipeViewState.Success -> {
                        val recipes = (state as RecipeViewState.Success).recipes
                        SuccessComponent(recipes = recipes, onSearchClicked = { query ->
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
            }
        }
    }

    //
    LaunchedEffect(Unit) {
        recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
    }
}

