package com.Tcc.Tcc.Model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field


data class ProfileModel(
    @Id
    val id: ObjectId = ObjectId(),
    var name: String,
    var number: String,
    var userPetName: String?,
    var userPetDescription: String?,
    @Field("user_id")
    val userId: String = id.timestamp.toString()
)