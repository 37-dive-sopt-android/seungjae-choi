package com.sopt.dive.presentation.signin

import android.widget.Toast
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
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.core.designsystem.component.SoptButton
import com.sopt.dive.core.designsystem.component.SoptFormField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extention.noRippleClickable
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.data.repository.RepositoryModule
import com.sopt.dive.presentation.common.ViewModelFactory
import com.sopt.dive.presentation.signin.state.SignInSideEffect

@Composable
fun SignInRoute(
    onSignUpClick: () -> Unit,
    onSignInSuccess: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val userManager = remember { UserManager(context) }

    val viewModel : SignInViewModel = viewModel(
        factory = remember {
            ViewModelFactory(
                authRepository = RepositoryModule.authRepository,
                userRepository = RepositoryModule.userRepository,
                openApiRepository = RepositoryModule.openApiRepository,
                userManager = userManager
            )
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignInSideEffect.ShowToast -> {
                        Toast.makeText(context, sideEffect.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    SignInScreen(
        username = uiState.username,
        password = uiState.password,
        onUsernameChange = viewModel::updateUsername,
        onPasswordChange = viewModel::updatePassword,
        onLaunchSignUp = onSignUpClick,
        onSignInSuccess = viewModel::login,
    )
}

@Composable
private fun SignInScreen(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLaunchSignUp: () -> Unit,
    onSignInSuccess: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                label = "USERNAME",
                value = username,
                onValueChange = onUsernameChange,
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
            username = "",
            password = "",
            onUsernameChange = {},
            onPasswordChange = {},
            onLaunchSignUp = {},
            onSignInSuccess = {}
        )
    }
}