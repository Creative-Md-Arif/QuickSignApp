package com.example.userregistrationapp.repository

import androidx.lifecycle.LiveData
import com.example.userregistrationapp.Dao.UserProfileDao
import com.example.userregistrationapp.model.UserProfile

class UserProfileRepositroy(private val userProfileDao: UserProfileDao) {

    fun getUserProfiles(): LiveData<List<UserProfile>>{
        return userProfileDao.getAllProfiles()
    }

    suspend fun insert(userProfile: UserProfile){
        return userProfileDao.insert(userProfile)
    }

    suspend fun update (userProfile: UserProfile){
        return userProfileDao.update(userProfile)
    }

    suspend fun delete(userProfile: UserProfile){
        return userProfileDao.delete(userProfile)
    }
}