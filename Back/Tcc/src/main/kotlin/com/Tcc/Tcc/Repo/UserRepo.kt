package com.Tcc.Tcc.Repo

import com.Tcc.Tcc.Model.UserModel
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepo : MongoRepository<UserModel, String> {

    fun findByUserId(userId: String): UserModel?
    fun findByEmail(email: String):UserModel?
    fun findByEmailAndPwd(email: String, pwd: String): UserModel?

}