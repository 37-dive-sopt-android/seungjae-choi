package com.sopt.dive.core.data

import android.content.Context
import android.content.SharedPreferences
import com.sopt.dive.core.data.UserManager.SharedPrefKeys.PREFS_NAME

data class UserData(
    val id: String,
    val password: String,
    val name: String,
    val nickname: String,
    val mbti: String
)

class UserManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    object SharedPrefKeys {
        const val PREFS_NAME = "user_prefs"
        const val AUTO_LOGIN = "auto_login"
        const val USER_ID = "user_id"
        const val USER_PASSWORD = "user_password"
        const val USER_NAME = "user_name"
        const val USER_NICKNAME = "user_nickname"
        const val USER_MBTI = "user_mbti"
    }

    fun loadUserData(): UserData {
        return UserData(
            id = sharedPreferences.getString(SharedPrefKeys.USER_ID, "") ?: "",
            password = sharedPreferences.getString(SharedPrefKeys.USER_PASSWORD, "") ?: "",
            name = sharedPreferences.getString(SharedPrefKeys.USER_NAME, "") ?: "",
            nickname = sharedPreferences.getString(SharedPrefKeys.USER_NICKNAME, "") ?: "",
            mbti = sharedPreferences.getString(SharedPrefKeys.USER_MBTI, "") ?: ""
        )
    }

    fun saveUserData(data: UserData) {
        sharedPreferences.edit().apply {
            putString(SharedPrefKeys.USER_ID, data.id)
            putString(SharedPrefKeys.USER_PASSWORD, data.password)
            putString(SharedPrefKeys.USER_NAME, data.name)
            putString(SharedPrefKeys.USER_NICKNAME, data.nickname)
            putString(SharedPrefKeys.USER_MBTI, data.mbti)
            putBoolean(SharedPrefKeys.AUTO_LOGIN, true)
            apply()
        }
    }

    fun isAutoLoginEnabled(): Boolean {
        return sharedPreferences.getBoolean(SharedPrefKeys.AUTO_LOGIN, false)
    }

    fun setAutoLogin(enabled: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(SharedPrefKeys.AUTO_LOGIN, enabled)
            apply()
        }
    }
}