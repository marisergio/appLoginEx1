package com.example.exlogin.util

import com.example.exlogin.Users
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EndPoint {

    @GET("agenda-procedimento")
    fun getUsers() : Call<List<Users>>

    @POST("agenda-procedimento")
    fun addUsers(@Body user : Users) : Call<Users>
}