package com.Tcc.Tcc.Controller




import com.Tcc.Tcc.Model.ProfileModel
import com.Tcc.Tcc.Repo.UserRepo
import com.Tcc.Tcc.Model.UserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/user")
class UserController(@Autowired val userRepo: UserRepo) {



    @GetMapping("/all")
    fun getCount(): ResponseEntity<MutableList<UserModel>> {
        val user = userRepo.findAll()
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity
            .notFound().build()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: String): ResponseEntity<UserModel> {
        val user = userRepo.findByUserId(id)
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity
            .notFound().build()
    }

    @GetMapping("/email/{email}")
    fun getUserByEmail(@PathVariable("email") email: String): ResponseEntity<UserModel> {
        val user = userRepo.findByEmail(email)
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity
            .notFound().build()
    }

    @GetMapping("/login")
    fun login(@RequestParam email: String,pwd: String ) : Boolean {
        val user = userRepo.findByEmailAndPwd(email,pwd)
        return user != null
    }




    @PostMapping("/insert")
    fun insert(@RequestBody user: UserModel): Any {
        val userExist = userRepo.findByEmail(email = user.email)
        return if (userExist != null)
            ResponseEntity.badRequest()
        else {
            userRepo.insert(user)
        }
    }

    @PutMapping("/update/{email}")
    fun update(@PathVariable("email") email: String, @RequestBody newUser: ProfileModel): UserModel? {
        val existingUser = userRepo.findByEmail(email)
        if (existingUser != null) {
            existingUser.name = newUser.name
            existingUser.number = newUser.number
            existingUser.userPetName = newUser.userPetName
            existingUser.userPetDescription = newUser.userPetDescription
            userRepo.save(existingUser)
        }
        return existingUser
    }




    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: String) {
        userRepo.findByUserId(id)?.let {
            userRepo.delete(it)
        }
    }


}