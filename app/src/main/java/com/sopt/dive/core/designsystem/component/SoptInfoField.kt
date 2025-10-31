package com.sopt.dive.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SoptInfoField (
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = value,
            fontSize = 18.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SoptInfoFieldPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        SoptInfoField(
            label = "ID",
            value = "sopt_official",
            modifier = Modifier.fillMaxWidth()
        )
    }
}