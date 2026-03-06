@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.planet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var reviewButtonEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            PizzaPlanetsTopAppBar(
                title = planet?.name ?: stringResource(R.string.app_name),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
            )
        },
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        if (planet != null) {
            PlanetDetailBody(
                planet = planet,
                pizzaList = pizzaList,
                reviewButtonEnabled = reviewButtonEnabled,
                onMenuSelectionUpdate = { pizzaId, selected ->
                    // TODO: update selected pizza
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            NoPlanet()
        }
    }

}

/**
 * @param planet
 * @param pizzaList
 * @param onMenuSelectionUpdate(Pizza ID, Selected)
 */
@Composable
private fun PlanetDetailBody(
    planet: Planet,
    pizzaList: List<Pizza>,
    reviewButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
    onMenuSelectionUpdate: (Int, Boolean) -> Unit = { _, _ -> },
) {
    Box(
        modifier = modifier,
    ) {
        LazyColumn {
            item {
                PlanetInfoHeader(planet)
            }
            item {
                MenuTitle()
            }
            items(pizzaList) { pizza ->
                PizzaMenuItem(
                    pizza = pizza,
                    onSelectionUpdate = {
                        onMenuSelectionUpdate(pizza.id, it)
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.height_list_bottom_offset))
                )
            }
        }
        ReviewButton(
            enabled = reviewButtonEnabled,
            onClick = {
                // TODO : navigate to review order screen
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(dimensionResource(R.dimen.padding_small))
        )
    }
}

@Composable
private fun PlanetInfoHeader(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Surface(
        shadowElevation = dimensionResource(R.dimen.elevation_shadow),
        tonalElevation = dimensionResource(R.dimen.elevation_tonal),
        modifier = modifier
    ) {
        Column {
            Image(
                painter = painterResource(planet.getPlanetDrawableByCode()),
                contentDescription = stringResource(R.string.planet_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.image_size))
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = stringResource(R.string.branch_title, planet.name),
                    style = MaterialTheme.typography.headlineLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = planet.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun MenuTitle(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.menu),
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    )
}

@Composable
private fun PizzaMenuItem(
    pizza: Pizza,
    modifier: Modifier = Modifier,
    onSelectionUpdate: (Boolean) -> Unit = {},
) {
    var checked by remember { mutableStateOf(false) }
    Card(modifier = modifier) {
        Column(modifier = modifier) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .height(dimensionResource(R.dimen.height_pizza_menu))
                    .fillMaxWidth()
            ) {
                Text(
                    text = pizza.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        onSelectionUpdate(checked)
                    }
                )
            }
            // TODO: add menu description expanded area
        }
    }

}

@Composable
private fun ReviewButton(
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    ElevatedButton(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.height_review_order_button))
    ) {
        Text(
            text = stringResource(R.string.review_order)
        )
    }
}


@Composable
private fun NoPlanet(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(stringResource(R.string.planet_does_not_exist))
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
            reviewButtonEnabled = false,
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
            reviewButtonEnabled = false,
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