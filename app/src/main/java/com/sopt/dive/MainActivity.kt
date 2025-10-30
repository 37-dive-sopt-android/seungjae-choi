package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.designsystem.component.SoptInfoField
import com.sopt.dive.core.designsystem.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId = intent.getStringExtra("id") ?: ""
        val userPassword = intent.getStringExtra("password") ?: ""
        val userName = intent.getStringExtra("name") ?: ""
        val userNickname = intent.getStringExtra("nickname") ?: ""
        val userMbti = intent.getStringExtra("mbti") ?: ""

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        userId = userId,
                        userPassword = userPassword,
                        userName = userName,
                        userNickname = userNickname,
                        userMbti = userMbti,
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    userId: String = "",
    userPassword: String = "",
    userName: String = "",
    userNickname: String = "",
    userMbti: String = ""
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Text(
                text = userName,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        Text(
            text = "안녕하세요. 안드로이드 YB ${userName}입니다.",
            modifier = Modifier
                .padding(top = 12.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SoptInfoField(
                label = "ID",
                value = userId,
                modifier = Modifier.fillMaxWidth()
            )

            SoptInfoField(
                label = "PW",
                value = userPassword,
                modifier = Modifier.fillMaxWidth()
            )

            SoptInfoField(
                label = "NICKNAME",
                value = userNickname,
                modifier = Modifier.fillMaxWidth()
            )

            SoptInfoField(
                label = "MBTI",
                value = userMbti,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    DiveTheme {
        MainScreen()
    }
}