package com.genuinecoder.springclient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seupet.databinding.PetsListItemBinding
import com.example.seupet.login.data.local.PetModel


class PetAdapter(petList: List<PetModel>?) : RecyclerView.Adapter<PetHolder>() {
    private val list: MutableList<PetModel> = petList?.toMutableList() ?: mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHolder {
        val binding =
            PetsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        holder.bind(list[position])
    }

}
