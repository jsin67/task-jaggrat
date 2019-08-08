package com.example.androidtest.repo

import androidx.lifecycle.MutableLiveData
import com.example.androidtest.models.User

interface UserRepo {

    fun getUserData(data: MutableLiveData<List<User>>, siteName: String)

}