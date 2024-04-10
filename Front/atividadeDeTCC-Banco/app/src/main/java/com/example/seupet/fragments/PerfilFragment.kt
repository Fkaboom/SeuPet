package com.example.seupet.main

import RetrofitService
import UserProfile
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.set
import androidx.recyclerview.widget.RecyclerView
import com.example.seupet.R
import com.example.seupet.core.hide
import com.example.seupet.core.show
import com.example.seupet.databinding.FragmentPerfilBinding
import com.example.seupet.login.data.local.PerfilModel
import com.example.seupet.login.data.local.UserModel
import com.genuinecoder.springclient.reotrfit.UserApi
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilFragment : Fragment() {


    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!
    private var userEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilBinding.inflate(inflater,container,false)
        userEmail = UserProfile(requireContext()).getEmail()

        binding.btPerfil.setOnClickListener {

            updateUser()



        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadUser()
    }

    private fun loadUser() {
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.retrofit!!.create(UserApi::class.java)
        userEmail?.let {
            userApi.findByEmail(it)
                ?.enqueue(object : Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        val user = response.body()
                        if (user != null) {
                            binding.nomePerfil.setText(user.name)
                            binding.phoneEditText.setText(user.number)
                            binding.petNomeEditText.setText(user.userPetName)
                            binding.petDescricao.setText(user.userPetDescription)
                        }
                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        Snackbar.make(binding.root, R.string.server_error_message, Snackbar.LENGTH_LONG).show()
                    }
                })
        }
    }

    private fun updateUser() {
        val retrofitService = RetrofitService()
        val userApi: UserApi = retrofitService.retrofit!!.create(UserApi::class.java)
        binding.pbLoading.hide()
        userEmail?.let {
            val nome = binding.nomePerfil.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val petname = binding.petNomeEditText.text.toString()
            val petDescription= binding.petDescricao.text.toString()
            userApi.update(userEmail!!, PerfilModel(name= nome, number = phone, userPetName = petname, userPetDescription = petDescription))
                ?.enqueue(object : Callback<UserModel?> {
                    override fun onResponse(
                        call: Call<UserModel?>,
                        response: Response<UserModel?>
                    ) {
                        response.code()
                        Log.d("Update Response", "Response: ${response.body()}")

                        Snackbar.make(binding.root, R.string.atualizado, Snackbar.LENGTH_LONG).show()
                        binding.pbLoading.hide()
                    }






                    override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                        Log.d("Update Error", "Error: ${t.message}")
                        Snackbar.make(binding.root, R.string.server_error_message, Snackbar.LENGTH_LONG).show()
                        binding.pbLoading.hide()
                    }
                })
        }
    }


}















