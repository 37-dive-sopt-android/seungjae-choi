package com.sopt.dive.presentation.home.data

import com.sopt.dive.R

sealed class FriendAction {
    data object None : FriendAction()
    data class Music(val title: String) : FriendAction()
    data object Gift : FriendAction()
}

data class MyProfile(
    val name: String,
    val statusMessage: String,
    val profileImageRes: Int
)

data class Friend(
    val id: Int,
    val name: String,
    val statusMessage: String,
    val profileImageRes: Int,
    val isBirthday: Boolean = false,
    val action: FriendAction
)

val myProfileData = MyProfile(
    name = "최승재",
    statusMessage = "안드 어렵다..",
    profileImageRes = R.drawable.profile
)

val dummyFriendList = listOf(
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