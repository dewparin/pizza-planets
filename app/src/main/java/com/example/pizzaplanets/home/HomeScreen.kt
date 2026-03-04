@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.home

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
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToPlanetDetail: (Int) -> Unit = {},
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
        HomeBody(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
) {
    Text(
        "Home Screen",
        modifier
    )
}