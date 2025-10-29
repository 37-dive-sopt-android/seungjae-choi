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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.data.UserData
import com.sopt.dive.core.data.UserManager
import com.sopt.dive.core.designsystem.component.SoptFormField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extention.showToast
import com.sopt.dive.core.util.Validator

class SignInActivity : ComponentActivity() {
    private var registeredId: String by mutableStateOf("")
    private var registeredPassword: String by mutableStateOf("")
    private var registeredName: String by mutableStateOf("")
    private var registeredNickname: String by mutableStateOf("")
    private var registeredMbti: String by mutableStateOf("")

    private lateinit var userManager: UserManager

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { intent ->
                val id = intent.getStringExtra(UserManager.SharedPrefKeys.USER_ID) ?: ""
                val pw = intent.getStringExtra(UserManager.SharedPrefKeys.USER_PASSWORD) ?: ""
                val name = intent.getStringExtra(UserManager.SharedPrefKeys.USER_NAME) ?: ""
                val nickname = intent.getStringExtra(UserManager.SharedPrefKeys.USER_NICKNAME) ?: ""
                val mbti = intent.getStringExtra(UserManager.SharedPrefKeys.USER_MBTI) ?: ""

                registeredId = id
                registeredPassword = pw
                registeredName = name
                registeredNickname = nickname
                registeredMbti = mbti

                userManager.saveUserData(UserData(id, pw, name, nickname, mbti))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        userManager = UserManager(this)

        checkAutoLogin()

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
                            signUpLauncher.launch(intent)
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

    private fun checkAutoLogin() {
        val autoLogin = userManager.isAutoLoginEnabled()

        val userData = userManager.loadUserData()

        registeredId = userData.id
        registeredPassword = userData.password
        registeredName = userData.name
        registeredNickname = userData.nickname
        registeredMbti = userData.mbti

        if (autoLogin && registeredId.isNotEmpty() && registeredPassword.isNotEmpty()) {
            val intent = Intent(this@SignInActivity, MainActivity::class.java).apply {
                putExtra(UserManager.SharedPrefKeys.USER_ID, registeredId)
                putExtra(UserManager.SharedPrefKeys.USER_PASSWORD, registeredPassword)
                putExtra(UserManager.SharedPrefKeys.USER_NAME, registeredName)
                putExtra(UserManager.SharedPrefKeys.USER_NICKNAME, registeredNickname)
                putExtra(UserManager.SharedPrefKeys.USER_MBTI, registeredMbti)
            }
            startActivity(intent)
            finish()
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
            .fillMaxSize()
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
                .clickable(
                    onClick = onLaunchSignUp,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        val validationError = Validator.validateSignInInputs(id, password)

                        if (validationError != null) {
                            context.showToast(validationError)
                            return@clickable
                        }

                        if (id == registeredId && password == registeredPassword) {
                            context.showToast("로그인에 성공했습니다!")
                            onSignInSuccess(
                                id,
                                password,
                                registeredName,
                                registeredNickname,
                                registeredMbti
                            )
                        } else {
                            context.showToast("아이디 또는 비밀번호가 틀렸습니다.")
                        }
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(bottom = 24.dp)
                .height(40.dp)
                .background(
                    color = Color.Magenta,
                    shape = RoundedCornerShape(4.dp)
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
private fun SignInScreenPreview() {
    SignInScreen(
        onLaunchSignUp = {},
        onSignInSuccess = { _, _, _, _, _ -> }
    )
}