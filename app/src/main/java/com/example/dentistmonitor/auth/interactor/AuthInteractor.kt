package com.example.dentistmonitor.auth.interactor

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.dentistmonitor.auth.model.AuthState
import com.example.dentistmonitor.auth.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow

private const val KEY_CREDENTIALS = "KEY_CREDENTIALS"

class AuthInteractor(
    private val repository: FirebaseRepository,
    private val sharedPreferences: SharedPreferences
) {

    fun observeAuth(): Flow<AuthState> = repository.observeAuth()

    fun signUp(email: String, password: String) {
        repository.signUp(email, password)
    }

    fun saveCredentials(credentials: String) {
        sharedPreferences.edit { putString(KEY_CREDENTIALS, credentials) }
    }

    fun isAuthorized() : Boolean = sharedPreferences.contains(KEY_CREDENTIALS)
}