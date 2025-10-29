package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.data.UserManager
import com.sopt.dive.core.designsystem.component.SoptFormField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extention.showToast
import com.sopt.dive.core.util.Validator

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpComplete = { id, pw, name, nickname, mbti ->
                            val resultIntent = Intent().apply {
                                putExtra(UserManager.SharedPrefKeys.USER_ID, id)
                                putExtra(UserManager.SharedPrefKeys.USER_PASSWORD, pw)
                                putExtra(UserManager.SharedPrefKeys.USER_NAME, name)
                                putExtra(UserManager.SharedPrefKeys.USER_NICKNAME, nickname)
                                putExtra(UserManager.SharedPrefKeys.USER_MBTI, mbti)
                            }
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        })
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpComplete: (String, String, String, String, String) -> Unit
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterName = remember { FocusRequester() }
    val focusRequesterNickname = remember { FocusRequester() }
    val focusRequesterMbti = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

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
                label = "ID",
                value = id,
                onValueChange = { id = it },
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
                onValueChange = { password = it },
                placeholder = "비밀번호를 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesterName.requestFocus() }
                ),
                textFieldModifier = Modifier
                    .focusRequester(focusRequesterPassword)
            )

            SoptFormField(
                label = "NAME",
                value = name,
                onValueChange = { name = it },
                placeholder = "이름을 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesterNickname.requestFocus() }
                ),
                textFieldModifier = Modifier
                    .focusRequester(focusRequesterName)
            )

            SoptFormField(
                label = "NICKNAME",
                value = nickname,
                onValueChange = { nickname = it },
                placeholder = "닉네임을 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesterMbti.requestFocus() }
                ),
                textFieldModifier = Modifier
                    .focusRequester(focusRequesterNickname)
            )

            SoptFormField(
                label = "MBTI",
                value = mbti,
                onValueChange = { mbti = it },
                placeholder = "MBTI를 입력해주세요.",
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                textFieldModifier = Modifier
                    .focusRequester(focusRequesterMbti)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp)
                .height(40.dp)
                .background(
                    color = Color.Magenta,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(
                    onClick = {
                        val errorMessage = Validator.validateSignUpInputs(
                            id, password, name, nickname, mbti
                        )

                        if (errorMessage != null) {
                            context.showToast(errorMessage)
                        } else {
                            context.showToast("회원가입에 성공했습니다!")
                            onSignUpComplete(id, password, name, nickname, mbti)
                        }
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "회원가입 하기",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(onSignUpComplete = { _, _, _, _, _ -> })
}