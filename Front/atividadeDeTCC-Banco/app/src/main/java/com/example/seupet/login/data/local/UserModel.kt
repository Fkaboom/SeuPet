package com.example.seupet.login.data.local


import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

data class UserModel(


    val name: String,
    val email: String,
    val number: String,
    val pwd: String,

)