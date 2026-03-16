@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.planet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzaplanets.R
import com.example.pizzaplanets.data.entity.Pizza
import com.example.pizzaplanets.data.entity.Planet
import com.example.pizzaplanets.qc.TEST_TAG_PLANET_DETAIL_SCREEN_MENU_CHECKBOX
import com.example.pizzaplanets.qc.TEST_TAG_PLANET_DETAIL_SCREEN_REVIEW_ORDER_BUTTON
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.screen.shared.NoPlanet
import com.example.pizzaplanets.ui.screen.shared.mockPizzaList
import com.example.pizzaplanets.ui.screen.shared.mockPlanet
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
import com.example.pizzaplanets.ui.utils.getPlanetDrawableByCode
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PlanetDetailScreen(
    planetId: Int,
    navigateBack: () -> Unit,
    navigateToReviewOrder: (Int, List<Int>) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlanetDetailViewModel = koinViewModel(
        parameters = {
            parametersOf(planetId)
        }
    )
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsState()
    val planet = uiState.planet
    val pizzaList = uiState.pizzaList ?: emptyList()
    val selectedPizzaIds = uiState.selectedPizzaIds

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
                selectedPizzaIds = selectedPizzaIds,
                reviewButtonEnabled = selectedPizzaIds.isNotEmpty(),
                onMenuSelectionUpdate = { pizzaId, selected ->
                    viewModel.togglePizzaSelection(pizzaId, selected)
                },
                onReviewOrderButtonClick = {
                    navigateToReviewOrder(planetId, selectedPizzaIds.toList())
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
    selectedPizzaIds: Set<Int>,
    reviewButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
    onMenuSelectionUpdate: (Int, Boolean) -> Unit = { _, _ -> },
    onReviewOrderButtonClick: () -> Unit = {},
) {
    Box(
        modifier = modifier,
    ) {
        LazyColumn {
            item {
                PlanetCoverImage(planet)
            }
            item {
                MenuTitle(
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.padding_small))
                )
            }
            items(pizzaList) { pizza ->
                PizzaMenuItem(
                    pizza = pizza,
                    checked = pizza.id in selectedPizzaIds,
                    onSelectionUpdate = {
                        onMenuSelectionUpdate(pizza.id, it)
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }
            item {
                PlanetInfo(
                    planet = planet,
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.padding_medium))
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
            onClick = onReviewOrderButtonClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun PlanetCoverImage(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(planet.getPlanetDrawableByCode()),
        contentDescription = stringResource(R.string.planet_image_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.cover_image_size))
    )
}

@Composable
private fun MenuTitle(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.menu),
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    )
}

@Composable
private fun PizzaMenuItem(
    pizza: Pizza,
    checked: Boolean,
    modifier: Modifier = Modifier,
    onSelectionUpdate: (Boolean) -> Unit = {},
) {
    Card(modifier = modifier) {
        Column(modifier = modifier) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(dimensionResource(R.dimen.height_pizza_menu))
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = pizza.name,
                    style = MaterialTheme.typography.displayMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = { onSelectionUpdate(it) },
                    modifier = Modifier
                        .testTag(
                            TEST_TAG_PLANET_DETAIL_SCREEN_MENU_CHECKBOX + pizza.id
                        )
                )
            }
            // TODO: add menu description expanded area
        }
    }

}

@Composable
private fun PlanetInfo(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Surface(
        tonalElevation = dimensionResource(R.dimen.elevation_tonal),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_small),
                    top = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_large),
                )
        ) {
            Text(
                text = stringResource(R.string.branch_title, planet.name),
                style = MaterialTheme.typography.displayMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = planet.description,
                style = MaterialTheme.typography.bodyLarge,
                // Dp.toSp() is only available inside a Density scope, so we use
                // LocalDensity.current to provide that scope via with(...)
                lineHeight = with(LocalDensity.current) { dimensionResource(R.dimen.text_line_height).toSp() },
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun ReviewButton(
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Button(
            enabled = enabled,
            onClick = onClick,
            modifier = Modifier
                .testTag(TEST_TAG_PLANET_DETAIL_SCREEN_REVIEW_ORDER_BUTTON)
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.height_review_order_button))
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(R.string.review_order),
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

//--- Preview Composables

@Preview
@Composable
private fun PlanetDetailBodyPreview() {
    PizzaPlanetsTheme {
        PlanetDetailBody(
            planet = mockPlanet,
            pizzaList = mockPizzaList,
            selectedPizzaIds = emptySet(),
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
            selectedPizzaIds = emptySet(),
            reviewButtonEnabled = false,
        )
    }
}
