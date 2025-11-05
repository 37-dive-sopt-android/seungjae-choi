package com.sopt.dive.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.presentation.home.component.FriendListItem
import com.sopt.dive.presentation.home.data.Friend
import com.sopt.dive.presentation.home.data.MyProfile
import com.sopt.dive.presentation.home.data.dummyFriendList
import com.sopt.dive.presentation.home.data.myProfileData

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    HomeScreen(
        paddingValues = paddingValues,
        myProfile = viewModel.myProfile,
        friendList = viewModel.friendList,
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    myProfile: MyProfile,
    friendList: List<Friend>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = paddingValues
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
    HomeScreen(
        paddingValues = PaddingValues(0.dp),
        myProfile = myProfileData,
        friendList = dummyFriendList
    )
}