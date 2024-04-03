package com.example.seupet.login

import RetrofitService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seupet.login.data.local.UserModel
import com.example.seupet.register.RegisterViewState
import com.genuinecoder.springclient.reotrfit.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Level
import java.util.logging.Logger

class LoginViewModel : ViewModel() {
    private val viewState = MutableLiveData<LoginViewState>()
    val state: LiveData<LoginViewState> = viewState

    fun validateInputs(email: String?, password: String?) {
        viewState.value = LoginViewState.ShowLoading
        if (email.isNullOrBlank() && password.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowErrorMessage
            return
        }

        if (email.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowEmailErrorMessage
            return
        }

        if (password.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowPasswordErrorMessage
            return
        }

        val retrofitService = RetrofitService()

        val clientApi: UserApi = retrofitService.retrofit!!.create(UserApi::class.java)
        clientApi.login(email,password)?.enqueue(object : Callback<Boolean?> {
            override fun onResponse(call: Call<Boolean?>, response: Response<Boolean?>) {
                if(response.body() == true)
                    viewState.value = LoginViewState.ShowHomeScreen
                else

                    viewState.value = LoginViewState.ShowErrorMessage

            }

            override fun onFailure(call: Call<Boolean?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        }
}





