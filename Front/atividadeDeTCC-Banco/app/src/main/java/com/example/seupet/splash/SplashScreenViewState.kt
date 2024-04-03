package com.example.seupet.splash

sealed class SplashScreenViewState {
    object ShowHome : SplashScreenViewState();
    object ShowLogin : SplashScreenViewState();
}