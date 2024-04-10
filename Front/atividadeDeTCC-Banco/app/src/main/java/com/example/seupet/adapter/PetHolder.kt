package com.genuinecoder.springclient.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.recyclerview.widget.RecyclerView
import com.example.seupet.databinding.PetsListItemBinding
import com.example.seupet.login.data.local.PetModel



class PetHolder(private val binding: PetsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pet: PetModel) {
        binding.color.text = pet.color
        binding.descricao.text = pet.descrition
        binding.mediaImage.setImageBitmap(StringToBitMap(pet.image))
        binding.local.text= pet.location
        binding.animal.text = pet.animal
        binding.race.text = pet.race
    }

}

fun StringToBitMap(encodedString: String?): Bitmap? {
    return try {
        val encodeByte: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
    } catch (e: Exception) {
        e.message
        null
    }
}