package com.sopt.dive.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.dive.R
import com.sopt.dive.presentation.home.model.Friend
import com.sopt.dive.presentation.home.model.FriendAction
import com.sopt.dive.presentation.home.model.HomeUiState
import com.sopt.dive.presentation.home.model.MyProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            myProfile = myProfileData,
            friendList = dummyFriendList
        )
    )

    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}


private val myProfileData = MyProfile(
    name = "최승재",
    statusMessage = "안드 어렵다..",
    profileImageRes = R.drawable.profile
)

private val dummyFriendList = listOf(
    Friend(1, "유재석", "무한도전~", R.drawable.profile, action = FriendAction.None),
    Friend(2, "아이유", "", R.drawable.profile, action = FriendAction.Music("Love wins all")),
    Friend(3, "차은우", "얼굴천재", R.drawable.profile, isBirthday = true, action = FriendAction.Gift),
    Friend(4, "카리나", "Next Level", R.drawable.profile, action = FriendAction.Music("Supernova - aespa")),
    Friend(5, "손흥민", "토트넘 돌아와줘 ㅠ", R.drawable.profile, action = FriendAction.None),
    Friend(6, "강호동", "", R.drawable.profile, action = FriendAction.None),
    Friend(7, "이효리", "", R.drawable.profile, action = FriendAction.None),
    Friend(8, "박보검", "구르미 그린 달빛", R.drawable.profile, isBirthday = true, action = FriendAction.Gift),
    Friend(9, "유재석", "무한도전~", R.drawable.profile, action = FriendAction.None),
    Friend(10, "아이유", "", R.drawable.profile, action = FriendAction.Music("Love wins all")),
    Friend(11, "차은우", "얼굴천재", R.drawable.profile, isBirthday = true, action = FriendAction.Gift),
    Friend(12, "카리나", "Next Level", R.drawable.profile, action = FriendAction.Music("Supernova - aespa")),
    Friend(13, "손흥민", "토트넘 돌아와줘 ㅠ", R.drawable.profile, action = FriendAction.None),
    Friend(14, "강호동", "", R.drawable.profile, action = FriendAction.None),
    Friend(15, "이효리", "", R.drawable.profile, action = FriendAction.None),
    Friend(16, "박보검", "구르미 그린 달빛", R.drawable.profile, isBirthday = true, action = FriendAction.Gift),
    Friend(17, "유재석", "무한도전~", R.drawable.profile, action = FriendAction.None),
    Friend(18, "아이유", "", R.drawable.profile, action = FriendAction.Music("Love wins all")),
    Friend(19, "차은우", "얼굴천재", R.drawable.profile, isBirthday = true, action = FriendAction.Gift),
    Friend(20, "카리나", "Next Level", R.drawable.profile, action = FriendAction.Music("Supernova - aespa")),
    Friend(21, "손흥민", "토트넘 돌아와줘 ㅠ", R.drawable.profile, action = FriendAction.None),
    Friend(22, "강호동", "", R.drawable.profile, action = FriendAction.None),
    Friend(23, "이효리", "", R.drawable.profile, action = FriendAction.None),
    Friend(24, "박보검", "구르미 그린 달빛", R.drawable.profile, isBirthday = true, action = FriendAction.Gift),
)