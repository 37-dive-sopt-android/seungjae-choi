package com.sopt.dive.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SoptFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    maxLines: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(bottom = 4.dp)
        )

        SoptTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = textFieldModifier,
            visualTransformation = visualTransformation,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SoptFormFieldPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        SoptFormField(
            label = "ID",
            value = "id",
            onValueChange = {},
            placeholder = "아이디를 입력해주세요.",
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
        )

        SoptFormField(
            label = "PW",
            value = "pw",
            onValueChange = {},
            placeholder = "아이디를 입력해주세요.",
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
            modifier = Modifier.padding(16.dp)
        )
    }

}