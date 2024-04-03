package com.example.seupet.register

import RegisterViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.seupet.R
import com.example.seupet.core.hide
import com.example.seupet.core.show
import com.example.seupet.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {



    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by viewModels();


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        binding.btRegister.setOnClickListener {

            viewModel.validateInputs(

                password = binding.pwd.text.toString(),
                email = binding.email.text.toString(),
                name = binding.nome.text.toString(),
                number = binding.number.text.toString()

            )
        }
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                RegisterViewState.ShowSuccesCreate -> showSuccesCreate()
                RegisterViewState.ShowErrorMessage -> showSnackError()
                RegisterViewState.ShowEmailErrorMessage -> showEmailError()
                RegisterViewState.ShowPasswordErrorMessage -> showPasswordError()
                RegisterViewState.ShowLoading -> showLoading()
                RegisterViewState.ShowNameError -> showNameError()
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showNameError() {
        binding.pbLoading.hide()
        binding.nome.error = getString(R.string.register_name_error_message)
    }

    private fun showEmailError() {
        binding.pbLoading.hide()
        binding.email.error = getString(R.string.register_email_error_message)
    }

    private fun showPasswordError() {
        binding.pbLoading.hide()
        binding.pwd.error = getString(R.string.register_pdw_error_message)
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error_message, Snackbar.LENGTH_LONG).show()
    }

    private fun showSuccesCreate() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.register_user_succes, Snackbar.LENGTH_LONG).show()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 1000)
    }
}