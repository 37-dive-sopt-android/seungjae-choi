package com.sopt.dive.presentation.mypage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.component.SoptButton
import com.sopt.dive.core.designsystem.component.SoptInfoField
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.UiState
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.presentation.common.ViewModelFactory
import com.sopt.dive.presentation.mypage.model.MyPageProfileUiModel
import com.sopt.dive.presentation.mypage.state.MyPageSideEffect

@Composable
fun MyPageRoute(
    paddingValues: PaddingValues,
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val userManager = remember { UserManager(context) }

    val viewModel: MyPageViewModel = viewModel(
        factory = remember {
            ViewModelFactory(userManager = userManager)
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is MyPageSideEffect.ShowToast -> {
                        Toast.makeText(context, sideEffect.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    when (val state = uiState.profileState) {
        is UiState.Success -> {
            MyPageScreen(
                paddingValues = paddingValues,
                modifier = modifier,
                profile = state.data,
                onLogoutClick = {
                    userManager.setAutoLogin(false)
                    navigateToSignIn()
                },
            )
        }
        is UiState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {}
    }
}

@Composable
private fun MyPageScreen(
    profile: MyPageProfileUiModel,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {
    val userName = profile.name
    val userUsername = profile.username
    val userEmail = profile.email
    val userAge = profile.age

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
                value = userAge,
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
            profile = MyPageProfileUiModel(
                name = "솝트",
                username = "sopt_user",
                email = "sopt@naver.com",
                age = "25"
            ),
            onLogoutClick = {},
            paddingValues = PaddingValues()
        )
    }
}