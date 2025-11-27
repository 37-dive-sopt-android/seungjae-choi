package com.sopt.dive.data.repository

import com.sopt.dive.data.model.FriendListModel

interface OpenApiRepository {
    suspend fun getUsers(
        page: Int
    ): Result<FriendListModel>
}