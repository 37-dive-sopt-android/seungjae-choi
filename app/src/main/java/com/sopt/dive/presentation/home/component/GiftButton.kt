package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.extention.noRippleClickable

@Composable
fun GiftButton(onClick: () -> Unit) {
    val shape = RoundedCornerShape(20.dp)
    val contentColor = Color.Red

    Box(
        modifier = Modifier
            .noRippleClickable(onClick = onClick)
            .border(BorderStroke(1.dp, contentColor), shape)
            .clip(shape)
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "선물하기",
                fontSize = 12.sp,
                color = contentColor // 색상 직접 지정
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_gift),
                contentDescription = "Gift",
                modifier = Modifier.size(16.dp),
                tint = contentColor // tint 직접 지정
            )
        }
    }
}