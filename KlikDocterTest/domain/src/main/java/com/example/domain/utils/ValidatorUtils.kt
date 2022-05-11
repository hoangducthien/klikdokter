package com.example.domain

import com.example.domain.base.PASSWORD_TOO_SHORT
import java.util.regex.Matcher
import java.util.regex.Pattern


val VALID_EMAIL_ADDRESS_REGEX: Pattern =
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

fun validateEmail(emailStr: String): Boolean {
    val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr)
    return matcher.find()
}

const val MIN_PASSWORD_LENGTH = 8

fun validatePassword(password: String): Int {
    if (password.length < MIN_PASSWORD_LENGTH) {
        return PASSWORD_TOO_SHORT
    }
    // further check...
    return 0
}