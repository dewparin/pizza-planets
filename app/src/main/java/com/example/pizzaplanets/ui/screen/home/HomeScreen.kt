@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.pizzaplanets.R
import com.example.pizzaplanets.entity.Planet
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = koinViewModel(),
    navigateToPlanetDetail: (Int) -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PizzaPlanetsTopAppBar(
                title = stringResource(R.string.app_name),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        HomeBody(
            planetList = uiState.planets,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeBody(
    planetList: List<Planet>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
       modifier = modifier
    ) {
        items(planetList) { planet ->
            PlanetItem(planet)
        }
    }
}

@Composable
fun PlanetItem(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Text(
            planet.name
        )
    }
}