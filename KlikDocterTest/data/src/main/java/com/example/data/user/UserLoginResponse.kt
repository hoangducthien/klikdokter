package com.example.data.user

import com.example.data.base.BaseResponse
import com.example.domain.user.User
import com.google.gson.annotations.SerializedName

class UserLoginResponse: BaseResponse() {
    @SerializedName("token")
    var token: String? = null
}