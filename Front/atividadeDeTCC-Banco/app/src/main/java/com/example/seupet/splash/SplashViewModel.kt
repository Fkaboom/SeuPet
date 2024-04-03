package com.example.seupet.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val viewState = MutableLiveData<SplashScreenViewState>()
    val state: LiveData<SplashScreenViewState> = viewState


    init {
        viewModelScope.launch {
//            if (useCase.isValidCache()) {
                viewState.value = SplashScreenViewState.ShowHome
//            } else {
                viewState.value = SplashScreenViewState.ShowLogin
            }
        }
    }
//}