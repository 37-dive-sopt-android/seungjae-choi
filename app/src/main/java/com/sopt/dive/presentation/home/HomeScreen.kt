package com.sopt.dive.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.core.util.UiState
import com.sopt.dive.data.local.UserManager
import com.sopt.dive.presentation.common.ViewModelFactory
import com.sopt.dive.presentation.home.component.FriendListItem
import com.sopt.dive.presentation.home.model.FriendAction
import com.sopt.dive.presentation.home.model.FriendUiModel
import com.sopt.dive.presentation.home.model.MyProfile
import com.sopt.dive.presentation.home.state.HomeSideEffect
import com.sopt.dive.presentation.home.state.HomeUiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val userManager = remember { UserManager(context) }

    val viewModel: HomeViewModel = viewModel(
        factory = remember {
            ViewModelFactory(userManager = userManager)
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.ShowToast -> {
                        Toast.makeText(context, sideEffect.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    when (val state = uiState.friendListState) {
        is UiState.Success -> {
            HomeScreen(
                paddingValues = paddingValues,
                myProfile = uiState.myProfile,
                friendList = state.data,
                modifier = modifier
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
private fun HomeScreen(
    paddingValues: PaddingValues,
    myProfile: MyProfile,
    friendList: ImmutableList<FriendUiModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = paddingValues,
    ) {
        item {
            MyProfileItem(profile = myProfile)
            Divider(
                color = Color.LightGray,
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        items(
            items = friendList,
            key = { it.id }
        ) { friend ->
            FriendListItem(friend = friend)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val previewState = HomeUiState(
        myProfile = MyProfile(
            name = "최승재",
            statusMessage = "안드 어렵다..",
            profileImageRes = R.drawable.profile
        ),
        friendListState = UiState.Success(
            data = listOf(
                FriendUiModel(
                    id = 1,
                    name = "유재석",
                    statusMessage = "무한도전~",
                    profileImageUrl = "sample_url",
                    action = FriendAction.None
                ),
                FriendUiModel(
                    id = 2,
                    name = "아이유",
                    statusMessage = "",
                    profileImageUrl = "sample_url",
                    action = FriendAction.Music("Love wins all")
                ),
                FriendUiModel(
                    id = 3,
                    name = "차은우",
                    statusMessage = "얼굴천재",
                    profileImageUrl = "sample_url",
                    isBirthday = true,
                    action = FriendAction.Gift
                )
            ).toImmutableList()
        )
    )

    HomeScreen(
        paddingValues = PaddingValues(0.dp),
        myProfile = previewState.myProfile,
        friendList = (previewState.friendListState as UiState.Success).data
    )
}