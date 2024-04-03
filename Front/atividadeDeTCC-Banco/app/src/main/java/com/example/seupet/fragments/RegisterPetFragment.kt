package com.example.seupet.fragments

import RetrofitService
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.seupet.PetApi
import com.example.seupet.R
import com.example.seupet.core.hide
import com.example.seupet.core.show
import com.example.seupet.databinding.FragmentRegisterPetBinding
import com.example.seupet.login.data.local.PetModel
import com.example.seupet.login.data.local.UserModel
import com.example.seupet.register.RegisterViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.ByteArrayOutputStream
import java.util.logging.Level
import java.util.logging.Logger


class RegisterPetFragment : Fragment() {

    private var pickImage = 0
    private var imageUri: Uri? = null
    private var request_image = 0
    private var imageBitmap: Bitmap? = null
    private var imageString: String? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            val source =
                imageUri?.let { ImageDecoder.createSource(requireActivity().contentResolver, it) }
            imageBitmap = source?.let { ImageDecoder.decodeBitmap(it) }
            binding.imagem.setImageBitmap(imageBitmap)
            imageString = imageBitmap?.let { BitMapToString(it) }
        }
        if (resultCode == RESULT_OK && requestCode == request_image) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imagem.setImageBitmap(imageBitmap)
            imageString = imageBitmap?.let { BitMapToString(it) }
        }
    }

    private var _binding: FragmentRegisterPetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterPetBinding.inflate(inflater, container, false)

        binding.apply {
            imagem.setOnClickListener {
                photoImport.show()

                selectGallery.setOnClickListener {
                    pickImage = 100
                    val gallery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                    startActivityForResult(gallery, pickImage)
                    photoImport.hide()

                }
                selectPhoto.setOnClickListener {
                    val photo = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    request_image = 100
                    startActivityForResult(photo,request_image)
                    photoImport.hide()
                }
            }
            val racesDog = resources.getStringArray(R.array.racesDog)
            val racesCat = resources.getStringArray(R.array.racesCat)
            val NoAnimal = resources.getStringArray(R.array.outro)

           selectAnimal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    var animal = NoAnimal


                    when(parent?.getItemAtPosition(position).toString()){
                        "Gato" -> animal = racesCat
                        "Cachorro" -> animal = racesDog
                        else -> "outro"
                    }


                    selectRace.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, animal)
                }

               override fun onNothingSelected(p0: AdapterView<*>?) {
                   TODO("Not yet implemented")
               }


           }

            btPet.setOnClickListener {

                val retrofitService = RetrofitService()

                val petApi: PetApi = retrofitService.retrofit!!.create(PetApi::class.java)
                val petModel = PetModel(image =  imageString, color = selectColor.selectedItem.toString(), descrition = petDescrition.text.toString(), location = petLocation.text.toString(), race = selectRace.selectedItem.toString(), animal = selectAnimal.selectedItem.toString())
                petApi.insert(petModel)?.enqueue(object : Callback<PetModel?> {
                    override fun onResponse(call: Call<PetModel?>, response: Response<PetModel?>) {
                        pbLoading.show()
                    }

                    override fun onFailure(call: Call<PetModel?>, t: Throwable) {
                        Logger.getLogger(PetModel::class.java.name)
                            .log(Level.SEVERE, "Error occurred", t)
                    }
                })
            }
                return binding.root
        }

    }
    fun BitMapToString(bitmap: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }


}