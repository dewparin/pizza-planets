@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.planet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzaplanets.R
import com.example.pizzaplanets.entity.Pizza
import com.example.pizzaplanets.entity.Planet
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
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
    val pizzaList = uiState.pizzaList ?: listOf()

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
                planet = planet,
                pizzaList = pizzaList,
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
    pizzaList: List<Pizza>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PlanetDetailSection(
            planet = planet,
        )
        PizzaMenuSection(
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small),
                top = dimensionResource(R.dimen.padding_large),
                end = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_small),
            ),
            pizzaList = pizzaList,
        )
    }
}

@Composable
private fun PlanetDetailSection(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shadowElevation = dimensionResource(R.dimen.elevation_shadow),
        tonalElevation = dimensionResource(R.dimen.elevation_tonal),
    ) {
        Column {
            PlanetHeaderImage(planet)
            PlanetInfo(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                planet = planet,
            )
        }
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
private fun PlanetInfo(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
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

@Composable
private fun PizzaMenuSection(
    pizzaList: List<Pizza>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.menu),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        LazyColumn {
            items(pizzaList) { pizza ->
                PizzaMenuItem(
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small)),
                    pizza = pizza,
                )
            }
        }
    }
}

@Composable
private fun PizzaMenuItem(
    pizza: Pizza,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(
            modifier = modifier,
        ) {
            Row(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .height(dimensionResource(R.dimen.pizza_menu_height))
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = pizza.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }

}

//--- Preview Composables

private val mockPlanet = Planet(
    id = 1,
    planetCode = "sat-1",
    name = "Moon",
    description = "Welcome to our closest cosmic kitchen! Moon Base Pizzeria serves up legendary low-gravity pies where the cheese stretches all the way to the ceiling. Try our signature \"Crater Crust Supreme\" — baked in ancient volcanic vents for that extra smoky flavor. Fun fact: our dough rises 6x higher here thanks to 1/6th Earth gravity. Every bite is literally out of this world!",
    travelDurationMs = 13_000,
)
private val mockPizzaList = listOf(
    Pizza(
        id = 1,
        planetId = 1,
        name = "Crater Crust Supreme",
        description = "Our legendary signature pie baked in ancient lunar volcanic vents. Loaded with smoked mozzarella, roasted garlic, and a ring of crispy crust shaped like a crater rim. The smoky flavor is literally geological.",
    ),
    Pizza(
        id = 2,
        planetId = 1,
        name = "The Dark Side Deluxe",
        description = "A mysterious half-and-half pizza — one side blazing hot with jalapeños and ghost pepper sauce, the other cool with ricotta and fresh basil. You never know which side you''ll bite into first.",
    ),
    Pizza(
        id = 3,
        planetId = 1,
        name = "Low-G Cheese Pull",
        description = "Apollo Classic', 'A tribute to the first humans who visited. Simple, timeless, and reliable — tomato sauce, fresh mozzarella, basil, and a drizzle of olive oil. One small bite for man, one giant flavor for mankind.",
    ),
)

@Preview
@Composable
private fun PlanetDetailBodyPreview() {
    PizzaPlanetsTheme {
        PlanetDetailBody(
            planet = mockPlanet,
            pizzaList = mockPizzaList,
        )
    }
}

@Preview
@Composable
private fun PlanetDetailBodyDarkThemePreview() {
    PizzaPlanetsTheme(darkTheme = true) {
        PlanetDetailBody(
            planet = Planet(
                id = 1,
                planetCode = "sat-1",
                name = "Moon",
                description = "Welcome to our closest cosmic kitchen! Moon Base Pizzeria serves up legendary low-gravity pies where the cheese stretches all the way to the ceiling. Try our signature \"Crater Crust Supreme\" — baked in ancient volcanic vents for that extra smoky flavor. Fun fact: our dough rises 6x higher here thanks to 1/6th Earth gravity. Every bite is literally out of this world!",
                travelDurationMs = 13_000,
            ),
            pizzaList = mockPizzaList,
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