package com.Tcc.Tcc.Repo

import com.Tcc.Tcc.Model.OngsModel
import org.springframework.data.mongodb.repository.MongoRepository

interface OngsRepo : MongoRepository<OngsModel, String> {
    fun findByOngsId(ongsId: String): OngsModel?

}