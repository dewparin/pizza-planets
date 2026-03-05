@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.planet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzaplanets.R
import com.example.pizzaplanets.entity.Planet
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme

@Composable
fun PlanetDetailScreen(
    planetId: Int,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,

) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PizzaPlanetsTopAppBar(
                // TODO: set planet name
                title = stringResource(R.string.app_name),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
            )
        }
    ) { innerPadding ->
        PlanetDetailBody(
            modifier = Modifier.padding(innerPadding),
        )
    }

}

@Composable
private fun PlanetDetailBody(
    modifier: Modifier = Modifier,
) {
    Text("Planet Detail", modifier)
}

@Preview
@Composable
private fun PlanetDetailBodyPreview() {
    PizzaPlanetsTheme {
        PlanetDetailBody()
    }
}