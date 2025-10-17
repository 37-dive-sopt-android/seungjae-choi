package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
                    SignUpScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    val context = LocalContext.current

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
            maxLines = 1
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
                .fillMaxWidth(),
            label = { Text("비밀번호를 입력해주세요.") },
            placeholder = { Text("비밀번호를 입력해주세요.") },
            maxLines = 1
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
                .fillMaxWidth(),
            label = { Text("이름을 입력해주세요.") },
            placeholder = { Text("이름을 입력해주세요.") },
            maxLines = 1
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
                .fillMaxWidth(),
            label = { Text("닉네임을 입력해주세요.") },
            placeholder = { Text("닉네임을 입력해주세요.") },
            maxLines = 1
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
                .fillMaxWidth(),
            label = { Text("MBTI를 입력해주세요.") },
            placeholder = { Text("MBTI를 입력해주세요.") },
            maxLines = 1
        )

        Spacer(
            modifier = Modifier
                .weight(1f)
        )

        Button(
            onClick = {
                val intent = Intent(context, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
            shape = RoundedCornerShape(4.dp)
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
    SignUpScreen()
}