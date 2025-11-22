package com.sopt.dive.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.sopt.dive.data.local.UserManager.SharedPrefKeys.PREFS_NAME

data class UserData(
    val id: String,
    val username: String,
    val name: String,
    val email: String,
    val age: Int,
)

class UserManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    object SharedPrefKeys {
        const val PREFS_NAME = "user_prefs"
        const val AUTO_LOGIN = "auto_login"
        const val USER_ID = "user_id"
        const val USER_USERNAME = "user_username"
        const val USER_NAME = "user_name"
        const val USER_EMAIL = "user_email"
        const val USER_AGE = "user_age"
    }

    fun loadUserData(): UserData {
        return UserData(
            id = sharedPreferences.getString(SharedPrefKeys.USER_ID, "").orEmpty(),
            username = sharedPreferences.getString(SharedPrefKeys.USER_USERNAME, "").orEmpty(),
            name = sharedPreferences.getString(SharedPrefKeys.USER_NAME, "").orEmpty(),
            email = sharedPreferences.getString(SharedPrefKeys.USER_EMAIL, "").orEmpty(),
            age = sharedPreferences.getInt(SharedPrefKeys.USER_AGE, 0)
        )
    }

    fun saveUserData(
        id: Int,
        username: String,
        name: String,
        email: String,
        age: Int
    ) {
        sharedPreferences.edit {
            putString(SharedPrefKeys.USER_ID, id.toString())
            putString(SharedPrefKeys.USER_USERNAME, username)
            putString(SharedPrefKeys.USER_NAME, name)
            putString(SharedPrefKeys.USER_EMAIL, email)
            putInt(SharedPrefKeys.USER_AGE, age)
            putBoolean(SharedPrefKeys.AUTO_LOGIN, true)
        }
    }

    fun setUserId(id: String) {
        sharedPreferences.edit { putString(SharedPrefKeys.USER_ID, id) }
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