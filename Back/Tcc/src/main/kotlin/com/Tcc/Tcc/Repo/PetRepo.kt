package com.Tcc.Tcc.Repo

import com.Tcc.Tcc.Model.PetModel
import org.springframework.data.mongodb.repository.MongoRepository


interface PetRepo : MongoRepository<PetModel, String> {

    fun findPetByColor(petColor: String): List<PetModel?>
    fun findPetByRace(petRace: String): List<PetModel?>
    fun findByPetId(petId: String): PetModel?
}