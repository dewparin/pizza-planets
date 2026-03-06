@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui.screen.review

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.pizzaplanets.R
import com.example.pizzaplanets.ui.PizzaPlanetsTopAppBar

@Composable
fun ReviewOrderScreen(
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
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        Text(
            "Order List Screen",
            modifier = Modifier.padding(innerPadding)
        )

    }
}