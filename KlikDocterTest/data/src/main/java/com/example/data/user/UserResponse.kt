package com.example.data.user

import com.example.data.base.BaseResponse
import com.example.domain.user.User
import com.google.gson.annotations.SerializedName

class UserResponse: BaseResponse() {
    @SerializedName("data")
    var user: User? = null
}