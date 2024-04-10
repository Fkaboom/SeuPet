package com.example.seupet.login
sealed class LoginViewState {
    object ShowEmailErrorMessage : LoginViewState()
    object ShowPasswordErrorMessage : LoginViewState()
    object ShowErrorMessage : LoginViewState()
    object ShowHomeScreen : LoginViewState()
    object ShowLoading : LoginViewState()
    object ShowRegister : LoginViewState()
    object ShowServerError : LoginViewState()

}