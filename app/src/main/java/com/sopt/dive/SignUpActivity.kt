package com.sopt.dive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.sopt.dive.ui.theme.DiveTheme

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
                                putExtra("id", id)
                                putExtra("password", pw)
                                putExtra("name", name)
                                putExtra("nickname", nickname)
                                putExtra("mbti", mbti)
                            }
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        })
                }
            }
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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

    fun validateInputs(): Boolean {
        val errorMessage = when {
            id.isBlank() -> "아이디를 입력해주세요."
            password.isBlank() -> "비밀번호를 입력해주세요."
            name.isBlank() -> "이름을 입력해주세요."
            nickname.isBlank() -> "닉네임을 입력해주세요."
            mbti.isBlank() -> "MBTI를 입력해주세요."

            id.length !in 6..10 -> "ID는 6~10자 이내로 입력해주세요."

            password.length !in 8..12 -> "비밀번호는 8~12자 이내로 입력해주세요."

            else -> null
        }

        return if (errorMessage != null) {
            showToast(context, errorMessage)
            false
        } else {
            true
        }
    }

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up",
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 40.dp)
        )

        Text(
            text = "ID",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(top = 40.dp, bottom = 4.dp)
        )

        TextField(
            value = id,
            onValueChange = { id = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("아이디를 입력해주세요.") },
            placeholder = { Text("아이디를 입력해주세요.") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequesterPassword.requestFocus() }
            )
        )

        Text(
            text = "PW",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(top = 24.dp, bottom = 4.dp)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesterPassword),
            label = { Text("비밀번호를 입력해주세요.") },
            placeholder = { Text("비밀번호를 입력해주세요.") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequesterName.requestFocus() }
            )
        )

        Text(
            text = "NAME",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(top = 24.dp, bottom = 4.dp)
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesterName),
            label = { Text("이름을 입력해주세요.") },
            placeholder = { Text("이름을 입력해주세요.") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequesterNickname.requestFocus() }
            )
        )

        Text(
            text = "NICKNAME",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(top = 24.dp, bottom = 4.dp)
        )

        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesterNickname),
            label = { Text("닉네임을 입력해주세요.") },
            placeholder = { Text("닉네임을 입력해주세요.") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequesterMbti.requestFocus() }
            )
        )

        Text(
            text = "MBTI",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(top = 24.dp, bottom = 4.dp)
        )

        TextField(
            value = mbti,
            onValueChange = { mbti = it },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesterMbti),
            label = { Text("MBTI를 입력해주세요.") },
            placeholder = { Text("MBTI를 입력해주세요.") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )

        Spacer(
            modifier = Modifier
                .weight(1f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .height(40.dp)
                .background(
                    color = Color.Magenta,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(
                    onClick = {
                        if (validateInputs()) {
                            showToast(context, "회원가입에 성공했습니다!")
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
fun SignUpScreenPreview() {
    SignUpScreen(onSignUpComplete = { _, _, _, _, _ -> })
}