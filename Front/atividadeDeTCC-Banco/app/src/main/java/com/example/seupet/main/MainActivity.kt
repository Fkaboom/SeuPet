package com.example.seupet.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.seupet.R
import com.example.seupet.databinding.ActivityMainBinding
import com.example.seupet.databinding.PetsListItemBinding
import com.example.seupet.fragments.OngsFragment
import com.example.seupet.fragments.PetsFragment
import com.example.seupet.fragments.RegisterPetFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(PetsFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.perfil -> replaceFragment(PerfilFragment())
                R.id.procurar -> replaceFragment(PetsFragment())
                R.id.ongs -> replaceFragment(OngsFragment())



            }
            true
        }


    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()


    }
}