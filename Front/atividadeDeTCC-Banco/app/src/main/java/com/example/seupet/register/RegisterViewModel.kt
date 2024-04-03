import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seupet.login.data.local.UserModel

import com.example.seupet.register.RegisterViewState
import com.genuinecoder.springclient.reotrfit.UserApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Level
import java.util.logging.Logger
import java.util.regex.Pattern

class RegisterViewModel() : ViewModel() {

    private val viewState = MutableLiveData<RegisterViewState>()
    val state: LiveData<RegisterViewState> = viewState

    fun validateInputs(number: String, name: String?, email: String?, password: String?) {
        var patternEmail = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
        val matcherEmail = patternEmail.matcher(email.toString())

        viewState.value = RegisterViewState.ShowLoading
        if (email.isNullOrBlank() && password.isNullOrBlank() && name.isNullOrBlank()) {
            viewState.value = RegisterViewState.ShowErrorMessage
            return
        }

        if (name.isNullOrBlank()) {
            viewState.value = RegisterViewState.ShowNameError
            return
        }

        if (!matcherEmail.matches()) {
            viewState.value = RegisterViewState.ShowEmailErrorMessage
            return
        }

        if (email.isNullOrBlank()) {
            viewState.value = RegisterViewState.ShowEmailErrorMessage
            return
        }

        if (password.isNullOrBlank()) {
            viewState.value = RegisterViewState.ShowPasswordErrorMessage
            return
        }

        viewModelScope.launch {

            val retrofitService = RetrofitService()

            val clientApi: UserApi = retrofitService.retrofit!!.create(UserApi::class.java)
            val userModel = UserModel(name = name, email = email, pwd = password, number = number)
            clientApi.insert(userModel)?.enqueue(object : Callback<UserModel?> {
                override fun onResponse(
                    call: Call<UserModel?>,
                    response: Response<UserModel?>
                ) {
                    viewState.value = RegisterViewState.ShowSuccesCreate
                }

                override fun onFailure(call: Call<UserModel?>, t: Throwable) {
                    viewState.value = RegisterViewState.ShowErrorMessage
                    Logger.getLogger(RegisterViewModel::class.java.name)
                        .log(Level.SEVERE, "Error occurred", t)
                }
            })
        }
    }




        }





