@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.order

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzaplanets.R
import com.example.pizzaplanets.entity.result.OrderQueryResult
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.screen.shared.mockOrderDetailList
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OrderListScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderListViewModel = koinViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsState()
    val orderDetailList = uiState.orderList

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
            orderDetailList = orderDetailList,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun OrderListBody(
    orderDetailList: List<OrderQueryResult>,
    modifier: Modifier = Modifier,
) {
    if (orderDetailList.isEmpty()) {
        NoOrder(modifier)
    } else {
        LazyColumn(
            modifier
        ) {
            items(orderDetailList) {
                OrderDetailItem(it)
            }
        }
    }
}

@Composable
private fun OrderDetailItem(
    orderDetail: OrderQueryResult,
    modifier: Modifier = Modifier,
) {
    val planet = orderDetail.planet
    val pizzaList = orderDetail.pizzaList
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row() {
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(dimensionResource(R.dimen.image_size)),
//                painter = painterResource(planet.getPlanetDrawableByCode()),
//                contentDescription = stringResource(R.string.planet_image_description),
//                contentScale = ContentScale.Crop,
//            )
            Column() {
                Text(
                    text = stringResource(R.string.branch_title, planet.name)
                )
            }
        }
    }

}

@Composable
private fun NoOrder(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(stringResource(R.string.no_order))
    }
}

//--- Preview Composables

@Preview
@Composable
fun OrderListBodyPreview() {
    PizzaPlanetsTheme {
        OrderListBody(
            orderDetailList = mockOrderDetailList,
        )
    }
}

@Preview
@Composable
fun OrderListBodyDarkThemePreview() {
    PizzaPlanetsTheme(darkTheme = true) {
        OrderListBody(
            orderDetailList = mockOrderDetailList,
        )
    }
}

@Preview
@Composable
private fun NoOrderPreview() {
    PizzaPlanetsTheme {
        NoOrder()
    }
}
