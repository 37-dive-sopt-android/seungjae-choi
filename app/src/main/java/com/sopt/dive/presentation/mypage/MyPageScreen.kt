package com.sopt.dive.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.component.SoptButton
import com.sopt.dive.core.designsystem.component.SoptInfoField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.data.local.UserManager


@Composable
fun MyPageRoute(
    paddingValues: PaddingValues,
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val userManager = remember { UserManager(context) }
    val userData = remember { userManager.loadUserData() }

    MyPageScreen(
        paddingValues = paddingValues,
        modifier = modifier,
        userUsername = userData.username,
        userName = userData.name,
        userEmail = userData.email,
        userAge = userData.age,
        onLogoutClick = {
            userManager.setAutoLogin(false)
            navigateToSignIn()
        }
    )
}

@Composable
private fun MyPageScreen(
    userUsername: String,
    userName: String,
    userEmail: String,
    userAge: Int,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = modifier
            .padding(paddingValues)
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
                contentDescription = "${userName} 프로필 이미지",
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
                label = "USERNAME",
                value = userUsername,
                modifier = Modifier.fillMaxWidth()
            )

            SoptInfoField(
                label = "EMAIL",
                value = userEmail,
                modifier = Modifier.fillMaxWidth()
            )

            SoptInfoField(
                label = "AGE",
                value = userAge.toString(),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        SoptButton(
            label = "로그아웃",
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    DiveTheme {
        MyPageScreen(
            userUsername = "sopt_user",
            userName = "솝트",
            userEmail = "sopt@naver.com",
            userAge = 25,
            onLogoutClick = {},
            paddingValues = PaddingValues()
        )
    }
}