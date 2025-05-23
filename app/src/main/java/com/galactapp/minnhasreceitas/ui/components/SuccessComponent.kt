package com.galactapp.minnhasreceitas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galactapp.minnhasreceitas.data.model.Meal

@Composable
fun SuccessComponent(recipes: List<Meal>, onSearchClicked: (query: String) -> Unit) {
    Column {
        Text(
            text = "Minhas Receitas",
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        SearchComponent(onSearchClicked = onSearchClicked)
        Spacer(modifier = Modifier.height(8.dp))
        RecipesList(recipes = recipes)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSuccessComponent() {
    SuccessComponent(recipes = emptyList(), onSearchClicked = {})
}