package com.example.domain.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User(@SerializedName("email") val email: String = "", val token: String = ""): Serializable {

    fun isLoggedIn(): Boolean {
        return email.isNotEmpty()
    }

}


