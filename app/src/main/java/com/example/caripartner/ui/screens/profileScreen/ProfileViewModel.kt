package com.example.caripartner.ui.screens.profileScreen

import androidx.lifecycle.ViewModel
import com.example.caripartner.data.model.User
import com.example.caripartner.data.repository.AuthRepository
import com.example.caripartner.data.repository.StorageRepository
import com.example.caripartner.data.repository.UserRepository

class ProfileViewModel(
    private val AuthRepository: AuthRepository = AuthRepository(),
    private val UserRepository: UserRepository = UserRepository(),
    private val StorageRepository: StorageRepository = StorageRepository()
):ViewModel(){
    fun GetUserData(callback:(User)-> Unit){
        UserRepository.GetUserLogin(AuthRepository.getUserId()){user ->
            callback(user)
        }
    }
}