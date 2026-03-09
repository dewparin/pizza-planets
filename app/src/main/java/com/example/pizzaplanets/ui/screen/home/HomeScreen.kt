@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.pizzaplanets.R
import com.example.pizzaplanets.entity.Planet
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
import com.example.pizzaplanets.ui.utils.durationToDisplayString
import com.example.pizzaplanets.ui.utils.getPlanetDrawableByCode
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = koinViewModel(),
    navigateToPlanetDetail: (Int) -> Unit = {},
    navigateToOrderListScreen: (() -> Unit) = {},
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
                navigateToOrderListScreen = navigateToOrderListScreen,
            )
        }
    ) { innerPadding ->
        HomeBody(
            modifier = Modifier.padding(innerPadding),
            planetList = uiState.planets,
            onItemClick = { planet ->
                navigateToPlanetDetail(planet.id)
            }
        )
    }
}

@Composable
private fun HomeBody(
    planetList: List<Planet>,
    modifier: Modifier = Modifier,
    onItemClick: (Planet) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(planetList) { planet ->
            PlanetItem(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium)),
                planet = planet,
                onClick = {
                    onItemClick(planet)
                }
            )
        }
    }
}

@Composable
private fun PlanetItem(
    planet: Planet,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier,
        onClick = onClick,
    ) {
        Column() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.image_size)),
                painter = painterResource(planet.getPlanetDrawableByCode()),
                contentDescription = stringResource(R.string.planet_image_description),
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    planet.name,
                    style = MaterialTheme.typography.headlineLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    stringResource(
                        R.string.planet_duration,
                        planet.durationToDisplayString(),
                    ),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
                text = planet.description,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}


@Preview
@Composable
private fun PlanetItemPreview() {
    PizzaPlanetsTheme {
        PlanetItem(
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
private fun HomeBodyPreview() {
    PizzaPlanetsTheme {
        HomeBody(
            listOf(
                Planet(
                    id = 1,
                    planetCode = "sat-1",
                    name = "Moon",
                    description = "Welcome to our closest cosmic kitchen! Moon Base Pizzeria serves up legendary low-gravity pies where the cheese stretches all the way to the ceiling. Try our signature \"Crater Crust Supreme\" — baked in ancient volcanic vents for that extra smoky flavor. Fun fact: our dough rises 6x higher here thanks to 1/6th Earth gravity. Every bite is literally out of this world!",
                    travelDurationMs = 13_000,
                ),
                Planet(
                    id = 2,
                    planetCode = "pln-1",
                    name = "Mercury",
                    description = "The fastest planet gets the fastest pizza! Mercury Magma Grill is famous for stone-fired pies cooked directly on the sun-facing surface at 430°C — no oven needed. Try our \"Speedy Gonzola\" topped with triple mozzarella that melts before it even hits the crust. Warning: delivery to the night side may arrive frozen. We recommend dining on the terminator line for the perfect temperature!",
                    travelDurationMs = 18_000,
                ),
                Planet(
                    id = 3,
                    planetCode = "pln-2",
                    name = "Venus",
                    description = "At Venus Cloud Nine Pizzeria, we bake at atmospheric pressure 90x stronger than Earth — giving our crusts an unmatched density and crunch. Our bestseller \"Acid Rain-bow\" features layers of tangy sauce inspired by our sulfuric skies. Dining happens in floating sky stations above the clouds where the view is absolutely gorgeous. Don''t worry, we provide heat-resistant bibs!",
                    travelDurationMs = 22_000,
                ),
            )
        )
    }
}