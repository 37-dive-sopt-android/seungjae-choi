package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class SignInActivity : ComponentActivity() {
    private var registeredId: String by mutableStateOf("")
    private var registeredPassword: String by mutableStateOf("")
    private var registeredName: String by mutableStateOf("")
    private var registeredNickname: String by mutableStateOf("")
    private var registeredMbti: String by mutableStateOf("")

    private val signUpLancher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { intent ->
                registeredId = intent.getStringExtra("id") ?: ""
                registeredPassword = intent.getStringExtra("password") ?: ""
                registeredName = intent.getStringExtra("name") ?: ""
                registeredNickname = intent.getStringExtra("nickname") ?: ""
                registeredMbti = intent.getStringExtra("mbti") ?: ""
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        registeredId = registeredId,
                        registeredPassword = registeredPassword,
                        registeredName = registeredName,
                        registeredNickname = registeredNickname,
                        registeredMbti = registeredMbti,
                        onLaunchSignUp = {
                            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                            signUpLancher.launch(intent)
                        },
                        onSignInSuccess = { id, pw, name, nickname, mbti ->
                            val intent =
                                Intent(this@SignInActivity, MainActivity::class.java).apply {
                                    putExtra("id", id)
                                    putExtra("password", pw)
                                    putExtra("name", name)
                                    putExtra("nickname", nickname)
                                    putExtra("mbti", mbti)
                                }
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun SignInScreen(
        modifier: Modifier = Modifier,
        registeredId: String = "",
        registeredPassword: String = "",
        registeredName: String = "",
        registeredNickname: String = "",
        registeredMbti: String = "",
        onLaunchSignUp: () -> Unit,
        onSignInSuccess: (id: String, pw: String, name: String, nickname: String, mbti: String) -> Unit
    ) {
        var id by remember(registeredId) { mutableStateOf(registeredId) }
        var password by remember(registeredPassword) { mutableStateOf(registeredPassword) }

        val focusRequesterPassword = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        val context = LocalContext.current

        Column(
            modifier = modifier
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome To SOPT",
                fontSize = 40.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 40.dp)
            )

            Text(
                text = "ID",
                fontSize = 20.sp,
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
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = "회원가입 하기",
                color = Color.LightGray,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable(
                        onClick = onLaunchSignUp,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
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
                            if (id.isEmpty() || password.isEmpty()) {
                                showToast(context, "아이디와 비밀번호를 입력해주세요.")
                                return@clickable
                            }

                            if (id == registeredId && password == registeredPassword) {
                                showToast(context, "로그인에 성공했습니다!")
                                onSignInSuccess(
                                    id,
                                    password,
                                    registeredName,
                                    registeredNickname,
                                    registeredMbti
                                )
                            } else {
                                showToast(context, "아이디 또는 비밀번호가 틀렸습니다.")
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome To SOPT",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SignInScreenPreview() {
        SignInScreen(
            onLaunchSignUp = {},
            onSignInSuccess = { _, _, _, _, _ -> }
        )
    }
}