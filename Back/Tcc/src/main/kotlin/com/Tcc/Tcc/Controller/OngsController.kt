package com.Tcc.Tcc.Controller






import com.Tcc.Tcc.Model.OngsModel
import com.Tcc.Tcc.Model.PetModel
import com.Tcc.Tcc.Repo.OngsRepo
import com.Tcc.Tcc.Repo.PetRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/ongs")
class OngsController(@Autowired val ongsRepo: OngsRepo) {




    @GetMapping("/all")
    fun getCount(): Int {
        return ongsRepo.findAll().count()
    }

    @GetMapping("/{id}")
    fun getPetById(@PathVariable("id") id: String): ResponseEntity<OngsModel> {
        val ongs = ongsRepo.findByOngsId(id)
        return if (ongs != null) ResponseEntity.ok(ongs) else ResponseEntity
            .notFound().build()
    }

    @PostMapping("/insert")
    fun postOngs(@RequestBody ongs: OngsModel): OngsModel {
        return ongsRepo.insert(ongs)
    }


    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable("id") id: String) {
        ongsRepo.findByOngsId(id)?.let {
            ongsRepo.delete(it)
        }
    }


}