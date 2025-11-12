package com.sopt.dive.presentation.search.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CardView(
    @DrawableRes imageRes: Int,
    contentDesc: String,
    rotationY: Float,
    modifier: Modifier = Modifier,
    alpha: Float = 1f,
    translationX: Float = 0f,
    translationY: Float = 0f
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = contentDesc,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer {
                this.rotationY = rotationY
                this.alpha = alpha
                this.translationX = translationX
                this.translationY = translationY
                cameraDistance = 12f * density
            }
            .clip(shape = RoundedCornerShape(24.dp))
    )

}