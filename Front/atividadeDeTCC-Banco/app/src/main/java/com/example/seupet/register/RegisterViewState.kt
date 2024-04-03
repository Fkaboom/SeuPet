package com.example.seupet.register

sealed class RegisterViewState {
    object ShowEmailErrorMessage : RegisterViewState()
    object ShowPasswordErrorMessage : RegisterViewState()
    object ShowErrorMessage : RegisterViewState()
    object ShowLoading : RegisterViewState()
    object ShowNameError :RegisterViewState()
    object ShowSuccesCreate : RegisterViewState()
}