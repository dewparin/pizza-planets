@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.order

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
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OrderListScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    orderListViewModel: OrderListViewModel = koinViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            PizzaPlanetsTopAppBar(
                title = stringResource(R.string.order_list),
                scrollBehavior = scrollBehavior,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        },
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        OrderListBody(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun OrderListBody(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Order List Screen",
        modifier = modifier
    )
}


//--- Preview Composables

@Preview
@Composable
fun OrderListBodyPreview() {
    PizzaPlanetsTheme {
        OrderListBody()
    }
}

@Preview
@Composable
fun OrderListBodyDarkThemePreview() {
    PizzaPlanetsTheme(darkTheme = true) {
        OrderListBody()
    }
}
