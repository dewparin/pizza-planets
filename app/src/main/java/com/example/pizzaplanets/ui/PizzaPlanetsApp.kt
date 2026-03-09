@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pizzaplanets.R
import com.example.pizzaplanets.ui.navigation.PizzaPlanetsNavHost
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme

@Composable
fun PizzaPlanetsApp(navHostController: NavHostController = rememberNavController()) {
    PizzaPlanetsNavHost(navHostController = navHostController)
}

@Composable
fun PizzaPlanetsTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
    navigateToOrderListScreen: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.padding_small)
                )
            ) {
                Text(
                    title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Icon(
                    imageVector = Icons.Outlined.RocketLaunch,
                    contentDescription = null,
                )
            }
        },
        actions = {
            navigateToOrderListScreen?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBasket,
                        contentDescription = stringResource(R.string.goto_order_list_description)
                    )
                }
            }

        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PizzaPlanetsTopAppBarPreview() {
    PizzaPlanetsTheme {
        PizzaPlanetsTopAppBar(
            title = stringResource(R.string.app_name),
            canNavigateBack = false,
        )
    }
}