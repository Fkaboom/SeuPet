package com.example.seupet

import com.example.seupet.login.data.local.PetModel
import com.example.seupet.login.data.local.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PetApi {

    @GET("/pet/all")
    fun allPets(): Call<List<PetModel>>




    @POST("/pet/insert")
    fun insert(@Body pet: PetModel?): Call<PetModel?>?
}