@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.planet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzaplanets.R
import com.example.pizzaplanets.entity.Planet
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
import com.example.pizzaplanets.ui.utils.durationToDisplayString
import com.example.pizzaplanets.ui.utils.getPlanetDrawableByCode
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PlanetDetailScreen(
    planetId: Int,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: PlanetDetailViewModel = koinViewModel(
        parameters = {
            parametersOf(planetId)
        }
    )
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsState()
    val planet = uiState.planet

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PizzaPlanetsTopAppBar(
                title = planet?.name ?: stringResource(R.string.app_name),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
            )
        }
    ) { innerPadding ->
        if (planet != null) {
            PlanetDetailBody(
                modifier = Modifier.padding(innerPadding),
                planet = planet
            )
        } else {
            NoPlanet()
        }
    }

}

@Composable
private fun NoPlanet(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(R.string.planet_does_not_exist))
    }
}

@Composable
private fun PlanetDetailBody(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Surface(
            shadowElevation = dimensionResource(R.dimen.elevation_shadow),
            tonalElevation = dimensionResource(R.dimen.elevation_tonal),
        ) {
            Column() {
                PlanetHeaderImage(planet)
                PlanetDetail(planet)
            }
        }
        // TODO: add menu
    }
}

@Composable
private fun PlanetHeaderImage(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.image_size)),
        painter = painterResource(planet.getPlanetDrawableByCode()),
        contentDescription = stringResource(R.string.planet_image_description),
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun PlanetDetail(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_small)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
    ) {
        Text(
            text = stringResource(R.string.branch_title, planet.name),
            style = MaterialTheme.typography.headlineLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = planet.description,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
private fun PlanetDetailBodyPreview() {
    PizzaPlanetsTheme {
        PlanetDetailBody(
            Planet(
                id = 1,
                planetCode = "sat-1",
                name = "Moon",
                description = "Welcome to our closest cosmic kitchen! Moon Base Pizzeria serves up legendary low-gravity pies where the cheese stretches all the way to the ceiling. Try our signature \"Crater Crust Supreme\" — baked in ancient volcanic vents for that extra smoky flavor. Fun fact: our dough rises 6x higher here thanks to 1/6th Earth gravity. Every bite is literally out of this world!",
                travelDurationMs = 13_000,
            )
        )
    }
}

@Preview
@Composable
private fun PlanetDetailBodyDarkThemePreview() {
    PizzaPlanetsTheme(darkTheme = true) {
        PlanetDetailBody(
            Planet(
                id = 1,
                planetCode = "sat-1",
                name = "Moon",
                description = "Welcome to our closest cosmic kitchen! Moon Base Pizzeria serves up legendary low-gravity pies where the cheese stretches all the way to the ceiling. Try our signature \"Crater Crust Supreme\" — baked in ancient volcanic vents for that extra smoky flavor. Fun fact: our dough rises 6x higher here thanks to 1/6th Earth gravity. Every bite is literally out of this world!",
                travelDurationMs = 13_000,
            )
        )
    }
}

@Preview
@Composable
private fun NoPlanetPreview() {
    PizzaPlanetsTheme {
        NoPlanet()
    }
}