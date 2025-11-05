package com.sopt.dive.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sopt.dive.presentation.home.data.Friend
import com.sopt.dive.presentation.home.data.MyProfile
import com.sopt.dive.presentation.home.data.dummyFriendList
import com.sopt.dive.presentation.home.data.myProfileData

class HomeViewModel : ViewModel() {
    var myProfile: MyProfile by mutableStateOf(myProfileData)
        private set

    var friendList: List<Friend> by mutableStateOf(dummyFriendList)
        private set
}