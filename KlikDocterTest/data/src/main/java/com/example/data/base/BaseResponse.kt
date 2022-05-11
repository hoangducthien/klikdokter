package com.example.data.base

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("message") val message: String = ""
)