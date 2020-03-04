package com.example.neostoreapplication.ui.Auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.neostoreapplication.Repositories.UserRepository
import com.example.neostoreapplication.ViewModel.SignUpViewModel


@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel(repository) as T
    }
}