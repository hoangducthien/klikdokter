package com.example.data.user

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    @FormUrlEncoded
    @POST("api/register")
    fun register(@Field("email") email: String, @Field("password") password: String): Call<UserResponse>

    @FormUrlEncoded
    @POST("api/auth/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<UserLoginResponse>

}