package com.sopt.dive.core.util

object Validator {

    fun validateSignUpInputs(
        id: String,
        password: String,
        name: String,
        nickname: String,
        mbti: String
    ): String? {
        return when {
            id.isBlank() -> "아이디를 입력해주세요."
            password.isBlank() -> "비밀번호를 입력해주세요."
            name.isBlank() -> "이름을 입력해주세요."
            nickname.isBlank() -> "닉네임을 입력해주세요."
            mbti.isBlank() -> "MBTI를 입력해주세요."
            id.length !in 6..10 -> "ID는 6~10자 이내로 입력해주세요."
            password.length !in 8..12 -> "비밀번호는 8~12자 이내로 입력해주세요."
            else -> null
        }
    }

    fun validateSignInInputs(id: String, password: String): String? {
        return when {
            id.isBlank() -> "아이디를 입력해주세요."
            password.isBlank() -> "비밀번호를 입력해주세요."
            else -> null
        }
    }
}