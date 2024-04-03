package com.Tcc.Tcc.Model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("pet")
data class PetModel(
    @Id
    val id: ObjectId = ObjectId(),
    val image: String? = null,
    val race: String? = null,
    val color: String? = null,
    val animal: String? = null,
    val descrition: String? = null,
    val location: String? = null,
    @Field("pet_id")
    val petId: String = id.timestamp.toString()
)