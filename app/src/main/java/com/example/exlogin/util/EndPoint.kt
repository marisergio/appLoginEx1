package com.example.exlogin.util

import com.example.exlogin.model.Users
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface EndPoint {

    @GET("agenda-procedimento")
    fun getUsers() : Call<List<Users>>

    @GET("convenios")
    fun getPaginatedItems(): Call<JsonObject>
    //fun getPaginatedItems(@Query("page") page: Int): Call<JsonArray>

    @GET("convenios/{idUser}")
    fun getUserById(@Path (value = "idUser") idUser : String?) : Call<Users>

    @POST("agenda-procedimento")
    fun addUsers(@Body user : Users) : Call<Users>

}