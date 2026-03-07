@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzaplanets.R
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme

@Composable
fun ReviewOrderScreen(
    planetId: Int,
    selectedPizzaIds: List<Int>,
    navigateBack: () -> Unit,
    navigateToOrderListScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
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
       ReviewOrderBody(
           modifier = Modifier.padding(innerPadding)
       )
    }
}

@Composable
fun ReviewOrderBody(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            OrderHeader()

        }
    }
    
}

@Composable
fun OrderHeader(
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
        // TODO: Set planet name
        Text(
            text = stringResource(R.string.branch_title, "Moon"),
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
        ReviewOrderBody()
    }
}

@Preview
@Composable
fun ReviewOrderBodyDarkThemePreview() {
    PizzaPlanetsTheme(darkTheme = true) {
        ReviewOrderBody()
    }
}
