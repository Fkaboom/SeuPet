package com.example.seupet.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seupet.core.show
import com.example.seupet.databinding.FragmentPerfilBinding

// TODO: Rename parameter arguments, choose names that match

private const val ARG_PARAM1 = "param1"

class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPerfilBinding.inflate(layoutInflater)
        return binding.root
    }




    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            PerfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}