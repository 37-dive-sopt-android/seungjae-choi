package com.sopt.dive.presentation.home

import android.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.presentation.home.component.FriendListItem
import com.sopt.dive.presentation.home.model.Friend
import com.sopt.dive.presentation.home.model.FriendAction
import com.sopt.dive.presentation.home.model.HomeUiState
import com.sopt.dive.presentation.home.model.MyProfile
import com.sopt.dive.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        paddingValues = paddingValues,
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    uiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = paddingValues
    ) {
        item {
            MyProfileItem(profile = uiState.myProfile)
            Divider(
                color = Color.LightGray,
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        items(
            items = uiState.friendList,
            key = { it.id }
        ) { friend ->
            FriendListItem(friend = friend)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        paddingValues = PaddingValues(0.dp),
        uiState = HomeUiState(
            myProfile = MyProfile(
                name = "최승재",
                statusMessage = "안드 어렵다..",
                profileImageRes = R.drawable.ic_menu_gallery
            ),
            friendList = listOf(
                Friend(
                    id = 1,
                    name = "유재석",
                    statusMessage = "무한도전~",
                    profileImageRes = R.drawable.ic_menu_gallery,
                    action = FriendAction.None
                ),
                Friend(
                    id = 2,
                    name = "아이유",
                    statusMessage = "",
                    profileImageRes = R.drawable.ic_menu_gallery,
                    action = FriendAction.Music("Love wins all")
                ),
                Friend(
                    id = 3,
                    name = "차은우",
                    statusMessage = "얼굴천재",
                    profileImageRes = R.drawable.ic_menu_gallery,
                    isBirthday = true,
                    action = FriendAction.Gift
                )
            )
        )
    )
}