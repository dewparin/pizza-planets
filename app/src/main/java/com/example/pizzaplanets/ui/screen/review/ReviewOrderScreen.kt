@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.review

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalPizza
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.example.pizzaplanets.R
import com.example.pizzaplanets.core.data.entity.Pizza
import com.example.pizzaplanets.core.data.entity.Planet
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.screen.shared.NoPlanet
import com.example.pizzaplanets.ui.screen.shared.mockPizzaList
import com.example.pizzaplanets.ui.screen.shared.mockPlanet
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
import com.example.pizzaplanets.ui.utils.getPlanetDrawableByCode
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
                onConfirmClick = {
                    viewModel.createOrder()
                    navigateToOrderListScreen()
                },
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
    onConfirmClick: () -> Unit = {},
) {
    Box(modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            item {
                Header(
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.padding_small))
                )
            }
            item {
                AddressInfo(
                    title = stringResource(R.string.deliver_from),
                    addressName = stringResource(R.string.branch_title, planet.name),
                    image = planet.getPlanetDrawableByCode(),
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.padding_large))
                )
            }
            item {
                AddressInfo(
                    title = stringResource(R.string.deliver_to),
                    addressName = stringResource(R.string.destination_address),
                    image = R.drawable.bangkok,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.padding_large))
                )
            }
            item {
                MenuTitle()
            }
            items(pizzaList) { pizza ->
                PizzaMenuItem(pizza)
            }
        }
        ConfirmButton(
            onClick = onConfirmClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.review_your_order),
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(bottom = dimensionResource(R.dimen.padding_small))
    )

}

@Composable
fun AddressInfo(
    title: String,
    addressName: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(R.dimen.padding_small),
                )
        )
        Card(
            shape = RoundedCornerShape(dimensionResource(R.dimen.padding_small)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = addressName,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_small),
                            bottom = dimensionResource(R.dimen.padding_medium),
                        )
                )
                Image(
                    painter = painterResource(image),
                    contentDescription = stringResource(R.string.planet_image_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.cover_image_small_size))
                )
            }
        }
    }
}

@Composable
private fun MenuTitle(
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        Text(
            text = stringResource(R.string.menu),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        Icon(
            imageVector = Icons.Outlined.LocalPizza,
            contentDescription = null,
        )
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
            .padding(horizontal = dimensionResource(R.dimen.padding_small))
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(MaterialTheme.colorScheme.onSurface)
        )
        Text(
            text = pizza.name,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
        )
    }
}

@Composable
private fun ConfirmButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.height_review_order_button))
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(R.string.confirm),
                style = MaterialTheme.typography.labelSmall,
            )
        }
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
