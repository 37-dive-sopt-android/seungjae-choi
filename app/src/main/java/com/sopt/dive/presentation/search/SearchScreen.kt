package com.sopt.dive.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    SearchScreen(
        paddingValues = paddingValues,
        modifier = modifier
    )
}

@Composable
private fun SearchScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(paddingValues)
    ) {
        Text(text = "Search Screen")
    }
}