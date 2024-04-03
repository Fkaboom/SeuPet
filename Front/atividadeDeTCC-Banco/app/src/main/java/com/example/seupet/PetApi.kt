package com.example.seupet

import com.example.seupet.login.data.local.PetModel
import com.example.seupet.login.data.local.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PetApi {

    @get:GET("/pet/all")
    val allPets: Call<List<Any?>?>?

    @POST("/pet/insert")
    fun insert(@Body pet: PetModel?): Call<PetModel?>?
}