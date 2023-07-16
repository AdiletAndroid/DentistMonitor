package com.example.dentistmonitor.auth.repository

import com.example.dentistmonitor.auth.model.AuthState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class FirebaseRepository(
    private val firebaseAuth: FirebaseAuth
) {

    private val currentState: MutableStateFlow<AuthState?> = MutableStateFlow(null)

    fun observeAuth(): Flow<AuthState> = currentState.filterNotNull()

    fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val credentials = "$email$password"
                    currentState.value = AuthState.Success(credentials)
                } else {
                    currentState.value = AuthState.Error(Throwable("Authorization failed"))
                }
            }
            .addOnFailureListener {
                currentState.value = AuthState.Error(it)
            }
    }

}