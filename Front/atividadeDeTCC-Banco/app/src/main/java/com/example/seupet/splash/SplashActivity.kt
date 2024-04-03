package com.example.seupet.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.seupet.R
import com.example.seupet.databinding.ActivitySplashBinding
import com.example.seupet.login.LoginActivity
import com.example.seupet.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class SplashActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initializeObserver()
        supportActionBar?.hide()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                SplashScreenViewState.ShowLogin -> showLogin()
                SplashScreenViewState.ShowHome -> showHome()
            }
        }
    }

    private fun showHome() {
        Snackbar.make(binding.root, R.string.sucess, Snackbar.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun showLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}