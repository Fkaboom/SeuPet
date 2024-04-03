package com.example.seupet.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.seupet.R
import com.example.seupet.core.hide
import com.example.seupet.core.show
import com.example.seupet.databinding.ActivityLoginBinding
import com.example.seupet.main.MainActivity
import com.example.seupet.register.RegisterActivity
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        binding.tvNewHere.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btLogin.setOnClickListener {
            viewModel.validateInputs(
                password = binding.pwd.text.toString(),
                email = binding.email.text.toString(),
            )
        }
    }


    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                LoginViewState.ShowHomeScreen -> showHome()
                LoginViewState.ShowErrorMessage -> showSnackError()
                LoginViewState.ShowEmailErrorMessage -> showEmailError()
                LoginViewState.ShowPasswordErrorMessage -> showPasswordError()
                LoginViewState.ShowLoading -> showLoading()
                LoginViewState.ShowRegister -> showRegister()
            }
        }
    }

    private fun showRegister() {
        binding.pbLoading.hide()
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showEmailError() {
        binding.pbLoading.hide()
        binding.email.error = getString(R.string.login_email_error_message)
    }

    private fun showPasswordError() {
        binding.pbLoading.hide()
        binding.pwd.error = getString(R.string.login_password_error_message)
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error_message, Snackbar.LENGTH_LONG).show()
    }

    private fun showHome() {
        binding.pbLoading.hide()
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
