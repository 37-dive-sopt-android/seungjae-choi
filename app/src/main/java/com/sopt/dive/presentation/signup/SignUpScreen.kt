package com.sopt.dive.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.data.UserData
import com.sopt.dive.core.data.UserManager
import com.sopt.dive.core.designsystem.component.SoptButton
import com.sopt.dive.core.designsystem.component.SoptFormField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extention.showToast
import com.sopt.dive.core.util.Validator

@Composable
fun SignUpRoute(
    onSignUpComplete: () -> Unit
) {
    val context = LocalContext.current
    val userManager = remember { UserManager(context) }

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    SignUpScreen(
        id = id, onIdChange = { id = it },
        password = password, onPasswordChange = { password = it },
        name = name, onNameChange = { name = it },
        nickname = nickname, onNicknameChange = { nickname = it },
        mbti = mbti, onMbtiChange = { mbti = it },
        onSignUpClick = {
            val errorMessage = Validator.validateSignUpInputs(id, password, name, nickname, mbti)
            if (errorMessage != null) {
                context.showToast(errorMessage)
            } else {
                userManager.saveUserData(UserData(id, password, name, nickname, mbti))
                context.showToast("회원가입에 성공했습니다!")

                onSignUpComplete()
            }
        }
    )
}

@Composable
private fun SignUpScreen(
    modifier: Modifier = Modifier,
    id: String, onIdChange: (String) -> Unit,
    password: String, onPasswordChange: (String) -> Unit,
    name: String, onNameChange: (String) -> Unit,
    nickname: String, onNicknameChange: (String) -> Unit,
    mbti: String, onMbtiChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Sign Up",
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 40.dp)
        )

        Column(
            modifier = Modifier.padding(top = 40.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SoptFormField(
                label = "ID", value = id, onValueChange = onIdChange,
                placeholder = "아이디를 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )
            SoptFormField(
                label = "PW", value = password, onValueChange = onPasswordChange,
                placeholder = "비밀번호를 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )
            SoptFormField(
                label = "NAME", value = name, onValueChange = onNameChange,
                placeholder = "이름을 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )
            SoptFormField(
                label = "NICKNAME", value = nickname, onValueChange = onNicknameChange,
                placeholder = "닉네임을 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )
            SoptFormField(
                label = "MBTI", value = mbti, onValueChange = onMbtiChange,
                placeholder = "MBTI를 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SoptButton(
            label = "회원가입 하기",
            onClick = onSignUpClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(
            id = "",
            onIdChange = {},
            password = "",
            onPasswordChange = {},
            name = "",
            onNameChange = {},
            nickname = "",
            onNicknameChange = {},
            mbti = "",
            onMbtiChange = {},
            onSignUpClick = {}
        )
    }
}