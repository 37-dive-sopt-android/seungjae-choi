package com.sopt.dive.data.repositoryimpl

import com.sopt.dive.core.util.suspendRunCatching
import com.sopt.dive.data.datasource.OpenApiDataSource
import com.sopt.dive.data.model.FriendListModel
import com.sopt.dive.data.model.toModel
import com.sopt.dive.data.repository.OpenApiRepository

class OpenApiRepositoryImpl(
    private val openApiDataSource: OpenApiDataSource
) : OpenApiRepository {
    override suspend fun getUsers(page: Int): Result<FriendListModel> =
        suspendRunCatching {
            openApiDataSource.getUsers(page = page).toModel()
        }
}