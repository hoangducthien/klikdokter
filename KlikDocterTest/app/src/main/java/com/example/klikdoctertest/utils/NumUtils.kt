package com.example.klikdoctertest.utils

import java.lang.Exception


fun String.safeInt(): Int {
    return try {
        this.toInt()
    } catch (e: Exception) {
        return 0
    }
}