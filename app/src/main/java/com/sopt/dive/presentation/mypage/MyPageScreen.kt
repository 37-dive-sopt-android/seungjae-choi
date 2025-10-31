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
import com.sopt.dive.core.data.UserManager
import com.sopt.dive.core.designsystem.component.SoptButton
import com.sopt.dive.core.designsystem.component.SoptInfoField
import com.sopt.dive.core.designsystem.theme.DiveTheme


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
        userId = userData.id,
        userPassword = userData.password,
        userName = userData.name,
        userNickname = userData.nickname,
        userMbti = userData.mbti,
        onLogoutClick = {
            userManager.setAutoLogin(false)
            navigateToSignIn()
        }
    )
}

@Composable
private fun MyPageScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    userId: String,
    userPassword: String,
    userName: String,
    userNickname: String,
    userMbti: String,
    onLogoutClick: () -> Unit
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
            paddingValues = PaddingValues(),
            userId = "sopt_official",
            userPassword = "123",
            userName = "솝트",
            userNickname = "SOPT",
            userMbti = "ISFJ",
            onLogoutClick = {}
        )
    }
}