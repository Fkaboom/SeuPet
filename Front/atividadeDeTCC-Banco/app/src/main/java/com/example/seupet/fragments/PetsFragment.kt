package com.example.seupet.fragments

import RetrofitService
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.seupet.PetApi
import com.example.seupet.R
import com.example.seupet.core.hide
import com.example.seupet.core.show
import com.example.seupet.databinding.FragmentPetsBinding
import com.example.seupet.login.LoginViewState
import com.example.seupet.login.data.local.PetModel
import com.genuinecoder.springclient.adapter.PetAdapter
import com.genuinecoder.springclient.reotrfit.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPetsBinding.inflate(inflater,container,false)

        recyclerView = binding.PetListRecyclerView

        binding.pbLoading.show()

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

    override fun onResume() {
        super.onResume()
        loadPets()
    }

    private fun loadPets() {
        val retrofitService = RetrofitService()
        val petApi: PetApi = retrofitService.retrofit!!.create(PetApi::class.java)
        petApi.allPets()
            ?.enqueue(object : Callback<List<PetModel>?> {
                override fun onResponse(
                    call: Call<List<PetModel>?>,
                    response: Response<List<PetModel>?>
                ) {
                    populateListView(response.body())
                }

                override fun onFailure(call: Call<List<PetModel>?>, t: Throwable) {

                }
            })
    }

    private fun populateListView(petList: List<PetModel>?) {
        val petAdapter = PetAdapter(petList)
        recyclerView!!.adapter = petAdapter
        binding.pbLoading.hide()
    }



}










