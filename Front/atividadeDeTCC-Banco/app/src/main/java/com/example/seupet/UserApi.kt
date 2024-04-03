package com.genuinecoder.springclient.reotrfit



import com.example.seupet.login.data.local.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @get:GET("/all")
    val allUsers: Call<List<Any?>?>?


    @GET("/user/login?")
    fun login(@Query("email") email:String, @Query("pwd") pwd: String): Call<Boolean>

    @POST("/user/insert")
    fun insert(@Body user: UserModel?): Call<UserModel?>?
}