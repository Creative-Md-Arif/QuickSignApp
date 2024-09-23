package com.example.userregistrationapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.userregistrationapp.database.UserDatabase
import com.example.userregistrationapp.model.UserProfile
import com.example.userregistrationapp.repository.UserProfileRepositroy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileViewModel(application: Application): AndroidViewModel(application){
    private val repositroy: UserProfileRepositroy

    init {
        val userProfileDao = UserDatabase.getDatabase(application).userProfileDao()
        repositroy = UserProfileRepositroy(userProfileDao)
    }

    fun getUserProfiles(): LiveData<List<UserProfile>>{
        return repositroy.getUserProfiles()
    }

   fun insertUserProfile(userProfile: UserProfile){
      viewModelScope.launch (Dispatchers.IO){
          repositroy.insert(userProfile)
      }
   }

    fun updateUserProfile(userProfile: UserProfile){
        viewModelScope.launch (Dispatchers.IO){
            repositroy.update(userProfile)
        }
    }

    fun deleteUserProfile(userProfile: UserProfile){
        viewModelScope.launch (Dispatchers.IO){
            repositroy.delete(userProfile)
        }
    }
}