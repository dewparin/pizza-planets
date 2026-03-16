@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.order

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzaplanets.R
import com.example.pizzaplanets.data.entity.OrderStatus
import com.example.pizzaplanets.data.entity.result.OrderQueryResult
import com.example.pizzaplanets.core.ui.widget.PizzaPlanetsTopAppBar
import com.example.pizzaplanets.ui.screen.shared.mockOrderDetailList
import com.example.pizzaplanets.core.ui.theme.PizzaPlanetsTheme
import com.example.pizzaplanets.ui.utils.getPlanetDrawableByCode
import com.example.pizzaplanets.ui.utils.statusColor
import com.example.pizzaplanets.ui.utils.statusIcon
import com.example.pizzaplanets.ui.utils.statusStringRes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OrderListScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OrderListViewModel = koinViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState by viewModel.uiState.collectAsState()
    val orderList = uiState.orderList

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
            orderList = orderList,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun OrderListBody(
    orderList: List<OrderQueryResult>,
    modifier: Modifier = Modifier,
) {
    if (orderList.isEmpty()) {
        NoOrder(modifier)
    } else {
        LazyColumn(
            modifier = modifier
        ) {
            items(orderList) {
                OrderDetailItem(
                    it,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
private fun OrderDetailItem(
    order: OrderQueryResult,
    modifier: Modifier = Modifier,
) {
    val orderInfo = order.orderInfo
    val planet = order.planet
    val pizzaList = order.pizzaList
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(planet.getPlanetDrawableByCode()),
                contentDescription = stringResource(R.string.planet_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.card_image_size))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(
                        start = dimensionResource(R.dimen.padding_small)
                    )
            ) {
                Text(
                    text = stringResource(R.string.branch_title, planet.name),
                    style = MaterialTheme.typography.displayMedium,
                )
                Text(
                    text = stringResource(R.string.order_menu_count, pizzaList.size),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
                )
                Text(
                    text = stringResource(orderInfo.orderStatus.statusStringRes()),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                )
            }
            if (orderInfo.orderStatus == OrderStatus.COMPLETED
                || orderInfo.orderStatus == OrderStatus.CANCELLED
            ) {
                StaticStatusBox(orderInfo.orderStatus)
            } else {
                AnimatedStatusBox(orderInfo.orderStatus)
            }
        }
    }

}

@Composable
fun StaticStatusBox(
    orderStatus: OrderStatus,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(dimensionResource(R.dimen.card_image_size))
            .clip(
                RoundedCornerShape(
                    topStart = dimensionResource(R.dimen.padding_small),
                    bottomStart = dimensionResource(R.dimen.padding_small),
                )
            )
            .background(orderStatus.statusColor())
    ) {
        Icon(
            imageVector = orderStatus.statusIcon(),
            contentDescription = orderStatus.toString(),
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Composable
fun AnimatedStatusBox(
    orderStatus: OrderStatus,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "gradient")
    val stopFraction by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "stopAnimation"
    )
    Box(
        modifier = modifier
            .size(dimensionResource(R.dimen.card_image_size))
            .clip(
                RoundedCornerShape(
                    topStart = dimensionResource(R.dimen.padding_small),
                    bottomStart = dimensionResource(R.dimen.padding_small),
                )
            )
            .background(
                Brush.horizontalGradient(
                    0.0f to MaterialTheme.colorScheme.surfaceVariant,
                    stopFraction to orderStatus.statusColor()
                )
            )
    ) {
        Icon(
            imageVector = orderStatus.statusIcon(),
            contentDescription = orderStatus.toString(),
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium))
        )
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
            orderList = mockOrderDetailList,
        )
    }
}

@Preview
@Composable
fun OrderListBodyDarkThemePreview() {
    PizzaPlanetsTheme(darkTheme = true) {
        OrderListBody(
            orderList = mockOrderDetailList,
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
