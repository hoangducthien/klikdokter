package com.example.domain.base

class DomainError(val errorCode: Int): Throwable()


const val UNKNOWN_ERROR = -1
const val EMAIL_NOT_VALID = 10000
const val PASSWORD_TOO_SHORT = 10001