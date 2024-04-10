package com.genuinecoder.springclient.reotrfit



import com.example.seupet.login.data.local.PerfilModel
import com.example.seupet.login.data.local.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("/user/email/{email}")
    fun findByEmail(@Path("email") email: String): Call<UserModel>


    @PUT("/user/update/{email}")
    fun update(@Path("email") email: String,@Body user: PerfilModel?): Call<UserModel?>?



    @GET("/user/login?")
    fun login(@Query("email") email:String, @Query("pwd") pwd: String): Call<Boolean>

    @POST("/user/insert")
    fun insert(@Body user: UserModel?): Call<UserModel?>?
}