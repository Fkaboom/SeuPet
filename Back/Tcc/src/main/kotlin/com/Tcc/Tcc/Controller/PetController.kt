package com.Tcc.Tcc.Controller







import com.Tcc.Tcc.Model.PetModel
import com.Tcc.Tcc.Repo.PetRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/pet")
class PetController(@Autowired val petRepo: PetRepo) {




    @GetMapping("/all")
    fun getCount(): Int {
        return petRepo.findAll().count()
    }

    @GetMapping("/{id}")
    fun getPetById(@PathVariable("id") id: String): ResponseEntity<PetModel> {
        val pet = petRepo.findByPetId(id)
        return if (pet != null) ResponseEntity.ok(pet) else ResponseEntity
            .notFound().build()
    }

    @PostMapping("/insert")
    fun postPet(@RequestBody pet: PetModel): PetModel {
        return petRepo.insert(pet)
    }

    @GetMapping("/race/{race}")
    fun getpetByrace(@PathVariable("race") race: String): ResponseEntity<List<PetModel?>> {
        val pet = petRepo.findPetByRace(race)
        return if (pet != null) ResponseEntity.ok(pet) else ResponseEntity
            .notFound().build()
    }

    @GetMapping("/color/{color}")
    fun getpetBycolor(@PathVariable("color") color: String): ResponseEntity<List<PetModel?>> {
        val pet = petRepo.findPetByColor(color)
        return if (pet != null) ResponseEntity.ok(pet) else ResponseEntity
            .notFound().build()
    }
    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable("id") id: String) {
        petRepo.findByPetId(id)?.let {
            petRepo.delete(it)
        }
    }

    @PatchMapping("/{id}")
    fun updatePet(@PathVariable("id") id: String): PetModel? {
        return petRepo.findByPetId(petId = id)?.let {
            petRepo.save(it)
        }
    }
}