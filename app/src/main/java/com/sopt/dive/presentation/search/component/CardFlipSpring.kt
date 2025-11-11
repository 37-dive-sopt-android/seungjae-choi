package com.sopt.dive.presentation.search.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sopt.dive.R
import com.sopt.dive.core.extention.noRippleClickable
import com.sopt.dive.presentation.search.model.CardFace

private const val SPRING_STIFFNESS = 177.8f
private const val SPRING_DAMPING = 0.75f

@Preview(showBackground = true)
@Composable
fun CardFlipSpring(modifier: Modifier = Modifier) {
    var currentState by remember { mutableStateOf(CardFace.Front) }
    val transition = updateTransition(currentState, label = "transition")

    val floatSpec = spring<Float>(
        stiffness = SPRING_STIFFNESS,
        dampingRatio = SPRING_DAMPING
    )
    val offsetSpec = spring<Offset>(
        stiffness = SPRING_STIFFNESS,
        dampingRatio = SPRING_DAMPING
    )

    val spin by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000) },
        label = "spin"
    ) { state ->
        if (state == CardFace.Front) 0f else 180f
    }

    val textAlpha by transition.animateFloat(
        transitionSpec = { floatSpec },
        label = "textAlpha",
    ) { state ->
        if (state == CardFace.Front) 0f else 1f
    }

    val backOffset by transition.animateOffset(
        transitionSpec = { offsetSpec },
        label = "backOffset"
    ) { state ->
        if (state == CardFace.Front) Offset(0f, 0f) else Offset(36f, 36f)
    }
    val repeatedText = remember {
        val baseText = "안두콩 화이팅! "
        baseText.repeat(100)
    }

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(24.dp))
                .background(color = Color.Cyan,)
                .zIndex(if (currentState == CardFace.Back) 1f else 0f)
        ) {
            Text(
                text = repeatedText,
                color = Color.Black.copy(alpha = textAlpha),
                modifier = Modifier.fillMaxSize()
            )
        }

        CardView(
            imageRes = R.drawable.zzanggu,
            contentDesc = "짱구 이미지",
            rotationY = spin,
            translationX = backOffset.x,
            translationY = backOffset.y
        )
    }
}