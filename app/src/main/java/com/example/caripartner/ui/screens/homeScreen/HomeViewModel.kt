package com.example.caripartner.ui.screens.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.caripartner.data.model.User
import com.example.caripartner.data.repository.AuthRepository
import com.example.caripartner.data.repository.StorageRepository
import com.example.caripartner.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.core.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await


class HomeViewModel(
    private val AuthRepository: AuthRepository = AuthRepository(),
    private val UserRepository: UserRepository = UserRepository(),
    private val StorageRepository: StorageRepository = StorageRepository()
) : ViewModel() {

    fun GetUserData(callback:(User)-> Unit){
        UserRepository.GetUserLogin(AuthRepository.getUserId()){user ->
            callback(user)
        }
    }

//    fun getAllUsersExceptCurrent(callback: (List<User>) -> Unit) {
//        UserRepository.getAllUsersExceptCurrent(callback)
//    }
}