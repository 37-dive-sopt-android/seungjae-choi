package com.sopt.dive.core.util

object Validator {
    private val PASSWORD_REGEX =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&+=])(?=\\S+$).{8,64}$".toRegex()

    fun validateSignUpInputs(
        username: String,
        password: String,
        name: String,
        email: String,
        ageText: String
    ): String? {
        val age = ageText.toIntOrNull()

        return when {
            username.isBlank() -> "사용자 이름을 입력해주세요."
            password.isBlank() -> "비밀번호를 입력해주세요."
            name.isBlank() -> "이름을 입력해주세요."
            email.isBlank() -> "이메일을 입력해주세요."
            ageText.isBlank() -> "나이를 입력해주세요."
            age == null || age <= 0 -> "나이는 0보다 큰 유효한 숫자여야 합니다."
            username.length !in 6..10 -> "사용자 이름은 6~10자 이내로 입력해주세요."
            !password.matches(PASSWORD_REGEX) -> "비밀번호는 공백 없이 8~64자이며, 대/소문자/숫자/특수문자를 각각 1자 이상 포함해야 합니다."
            else -> null
        }
    }

    fun validateSignInInputs(username: String, password: String): String? {
        return when {
            username.isBlank() -> "사용자 이름을 입력해주세요."
            password.isBlank() -> "비밀번호를 입력해주세요."
            else -> null
        }
    }
}