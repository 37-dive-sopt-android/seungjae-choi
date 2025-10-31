package com.sopt.dive.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    HomeScreen(
        paddingValues = paddingValues,
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(paddingValues)
    ) {
        Text(text = "Home Screen")
    }
}