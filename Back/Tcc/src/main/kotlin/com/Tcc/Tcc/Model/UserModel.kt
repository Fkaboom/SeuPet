package com.Tcc.Tcc.Model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document("user")
data class UserModel(
    @Id
    val id: ObjectId = ObjectId(),
    var name: String,
    val email: String,
    val pwd: String,
    var number: String,
    var userPetName: String?,
    var userPetDescription: String?,
    @Field("user_id")
    val userId: String = id.timestamp.toString()
)



