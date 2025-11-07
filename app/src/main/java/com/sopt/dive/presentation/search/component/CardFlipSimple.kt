package com.sopt.dive.presentation.search.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.extention.noRippleClickable
import com.sopt.dive.presentation.search.model.CardFace

@Composable
fun CardFlipSimple(modifier: Modifier = Modifier) {
    var currentState by remember { mutableStateOf(CardFace.Front) }
    val targetSpin = if (currentState == CardFace.Front) 0f else 180f

    val spin by animateFloatAsState(
        targetValue = targetSpin,
        animationSpec = tween(durationMillis = 1000),
        label = "spin"
    )

    Box(
        modifier = modifier
            .size(200.dp, 300.dp)
            .noRippleClickable {
                currentState = when (currentState) {
                    CardFace.Front -> CardFace.Back
                    CardFace.Back -> CardFace.Front
                }
            },
        contentAlignment = Alignment.Center
    ) {
        CardView(
            imageRes = R.drawable.zzanggu,
            contentDesc = "짱구 이미지",
            rotationY = spin,
            alpha = if (spin <= 90f) 1f else 0f
        )

        CardView(
            imageRes = R.drawable.hunni,
            contentDesc = "훈이 이미지",
            rotationY = spin - 180f,
            alpha = if (spin > 90f) 1f else 0f
        )
    }
}