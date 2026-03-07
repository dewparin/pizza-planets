@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzaplanets.R
import com.example.pizzaplanets.entity.Pizza
import com.example.pizzaplanets.entity.Planet
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.screen.shared.NoPlanet
import com.example.pizzaplanets.ui.screen.shared.mockPizzaList
import com.example.pizzaplanets.ui.screen.shared.mockPlanet
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ReviewOrderScreen(
    planetId: Int,
    selectedPizzaIds: List<Int>,
    navigateBack: () -> Unit,
    navigateToOrderListScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReviewOrderViewModel = koinViewModel(
        parameters = {
            parametersOf(
                planetId,
                selectedPizzaIds,
            )
        }
    )
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsState()
    val planet = uiState.planet
    val selectedPizzaList = uiState.pizzaList ?: emptyList()

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PizzaPlanetsTopAppBar(
                title = stringResource(R.string.app_name),
                scrollBehavior = scrollBehavior,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        }
    ) { innerPadding ->
        if (planet != null) {
            ReviewOrderBody(
                planet = planet,
                pizzaList = selectedPizzaList,
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            NoPlanet()
        }
    }
}

@Composable
fun ReviewOrderBody(
    planet: Planet,
    pizzaList: List<Pizza>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            OrderHeader(planet = planet)
        }
        items(pizzaList) { pizza ->
            PizzaMenuItem(pizza)
        }
    }

}

@Composable
private fun PizzaMenuItem(
    pizza: Pizza,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(MaterialTheme.colorScheme.onSurface)
        )
        Text(
            text = pizza.name,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
        )
    }
}

@Composable
fun OrderHeader(
    planet: Planet,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.review_your_order),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.branch_title, planet.name),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
        Row {
            Text(
                "${stringResource(R.string.deliver_to)} ",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                stringResource(R.string.destination_address),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Text(
            text = stringResource(R.string.menu),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

//--- Preview Composables

@Preview
@Composable
fun ReviewOrderBodyPreview() {
    PizzaPlanetsTheme {
        ReviewOrderBody(
            planet = mockPlanet,
            pizzaList = mockPizzaList,
        )
    }
}

@Preview
@Composable
fun ReviewOrderBodyDarkThemePreview() {
    PizzaPlanetsTheme(darkTheme = true) {
        ReviewOrderBody(
            planet = mockPlanet,
            pizzaList = mockPizzaList,
        )
    }
}
