package com.example.pizzaplanets.core.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzaplanets.core.ui.R
import com.example.pizzaplanets.core.ui.theme.PizzaPlanetsTheme

@Composable
fun NoPlanet(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(stringResource(R.string.planet_does_not_exist))
    }
}

@Preview
@Composable
private fun NoPlanetPreview() {
    PizzaPlanetsTheme {
        NoPlanet()
    }
}