package com.example.userregistrationapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.userregistrationapp.database.UserDatabase
import com.example.userregistrationapp.model.UserProfile
import com.example.userregistrationapp.repository.UserProfileRepositroy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

class UserProfileViewModel(application: Application): AndroidViewModel(application){
    private val repositroy: UserProfileRepositroy

    init {
        val userProfileDao = UserDatabase.getDatabase(application).userProfileDao()
        repositroy = UserProfileRepositroy(userProfileDao)
    }

    fun getUserProfiles() = repositroy.getUserProfiles()

    fun insertUserProfile(userProfile: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            repositroy.insert(userProfile)
        }
    }

    suspend fun updateUserProfile(userProfile: UserProfile): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                repositroy.update(userProfile)
                true // Return true if update was successful
            } catch (e: Exception) {
                false // Return false if update failed
            }
        }
    }

    fun deleteUserProfile(userProfile: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            repositroy.delete(userProfile)
        }
    }
}
