package com.example.seupet.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seupet.R
import com.example.seupet.core.hide
import com.example.seupet.core.show
import com.example.seupet.databinding.ActivityMainBinding
import com.example.seupet.databinding.FragmentPetsBinding
import com.example.seupet.databinding.FragmentRegisterPetBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PetsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PetsFragment : Fragment() {



    private var _binding: FragmentPetsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPetsBinding.inflate(inflater,container,false)


        binding.btpet.setOnClickListener{


            binding.pbLoading.show()



                val secondFragment =RegisterPetFragment()
                val transaction=requireActivity().supportFragmentManager
                    .beginTransaction()
                transaction.replace(R.id.frame_layout,secondFragment)
                transaction.commit()
            }
        return binding.root
        }

    }


