package com.Tcc.Tcc.Model


import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("ongs")
data class OngsModel(
    @Id
    val id: ObjectId = ObjectId(),
    val name: String,
    val pwd: String,
    val number: String,
    @Field("ongs_id")
    val ongsId: String = id.timestamp.toString()
)