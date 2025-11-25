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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.core.designsystem.component.SoptButton
import com.sopt.dive.core.designsystem.component.SoptFormField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extention.showToast
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.repository.RepositoryModule
import com.sopt.dive.presentation.common.ViewModelFactory
import com.sopt.uniqlo.core.util.UiState

@Composable
fun SignUpRoute(
    onSignUpComplete: () -> Unit
) {
    val context = LocalContext.current
    val userManager = remember { UserManager(context) }

    val viewModel : SignUpViewModel = viewModel(
        factory = remember {
            ViewModelFactory(
                authRepository = RepositoryModule.authRepository,
                userRepository = RepositoryModule.userRepository,
                userManager = userManager
            )
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.registerState) {
        when (val state = uiState.registerState) {
            is UiState.Success -> {
                context.showToast("회원가입 성공!")
                onSignUpComplete()
            }
            is UiState.Failure -> {
                context.showToast(state.msg)
            }
            else -> {}
        }
    }

    SignUpScreen(
        username = uiState.username,
        onUsernameChange = viewModel::updateUsername,
        password = uiState.password,
        onPasswordChange = viewModel::updatePassword,
        name = uiState.name,
        onNameChange = viewModel::updateName,
        email = uiState.email,
        onEmailChange = viewModel::updateEmail,
        age = uiState.age,
        onAgeChange = viewModel::updateAge,
        onSignUpClick = viewModel::register,
    )
}

@Composable
private fun SignUpScreen(
    username: String,
    password: String,
    name: String,
    email: String,
    age: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
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
                label = "USERNAME", value = username, onValueChange = onUsernameChange,
                placeholder = "사용자 이름을 입력해주세요.",
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
                label = "EMAIL", value = email, onValueChange = onEmailChange,
                placeholder = "이메일을 입력해주세요.",
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
                label = "AGE", value = age, onValueChange = onAgeChange,
                placeholder = "나이를 입력해주세요.",
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
            onClick = { onSignUpClick() },
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
            username = "",
            password = "",
            name = "",
            email = "",
            age = "",
            onUsernameChange = {},
            onPasswordChange = {},
            onNameChange = {},
            onEmailChange = {},
            onAgeChange = {},
            onSignUpClick = {}
        )
    }
}