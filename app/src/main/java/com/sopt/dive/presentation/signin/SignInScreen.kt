package com.sopt.dive.presentation.signin

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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.data.UserManager
import com.sopt.dive.core.designsystem.component.SoptButton
import com.sopt.dive.core.designsystem.component.SoptFormField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extention.noRippleClickable
import com.sopt.dive.core.extention.showToast
import com.sopt.dive.core.util.Validator

@Composable
fun SignInRoute(
    onSignUpClick: () -> Unit,
    onSignInSuccess: () -> Unit
) {
    val context = LocalContext.current
    val userManager = remember { UserManager(context) }
    val userData = remember { userManager.loadUserData() }

    var id by remember(userData.id) { mutableStateOf(userData.id) }
    var password by remember(userData.password) { mutableStateOf(userData.password) }

    SignInScreen(
        id = id,
        password = password,
        onIdChange = { id = it },
        onPasswordChange = { password = it },
        onLaunchSignUp = onSignUpClick,
        onSignInSuccess = {
            val validationError = Validator.validateSignInInputs(id, password)
            if (validationError != null) {
                context.showToast(validationError)
                return@SignInScreen
            }

            if (id == userData.id && password == userData.password) {
                context.showToast("로그인에 성공했습니다!")
                userManager.setAutoLogin(true)
                onSignInSuccess()
            } else {
                context.showToast("아이디 또는 비밀번호가 틀렸습니다.")
            }
        }
    )
}

@Composable
private fun SignInScreen(
    modifier: Modifier = Modifier,
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLaunchSignUp: () -> Unit,
    onSignInSuccess: () -> Unit
) {
    val focusRequesterPassword = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Welcome To SOPT",
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
                label = "ID",
                value = id,
                onValueChange = onIdChange,
                placeholder = "아이디를 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesterPassword.requestFocus() }
                ),
            )

            SoptFormField(
                label = "PW",
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "비밀번호를 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                textFieldModifier = Modifier.focusRequester(focusRequesterPassword),
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "회원가입 하기",
            color = Color.LightGray,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .noRippleClickable(onClick = onLaunchSignUp)
        )

        SoptButton(
            label = "로그인 하기",
            onClick = onSignInSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DiveTheme {
        SignInScreen(
            id = "",
            password = "",
            onIdChange = {},
            onPasswordChange = {},
            onLaunchSignUp = {},
            onSignInSuccess = {}
        )
    }
}