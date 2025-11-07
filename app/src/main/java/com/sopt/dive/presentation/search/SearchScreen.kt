package com.sopt.dive.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.presentation.search.component.CardFlipSimple
import com.sopt.dive.presentation.search.component.CardFlipSpring

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
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardFlipSimple()

        Spacer(modifier = Modifier.size(16.dp))

        CardFlipSpring()
    }
}

enum class CardFace { Front, Back }

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        paddingValues = PaddingValues(0.dp)
    )
}