package com.example.androidtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtest.models.User
import com.example.androidtest.repo.UserRepo
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {
    var userLiveData: MutableLiveData<List<User>> = MutableLiveData()

    /**
     * Disables item in the list and refresh the data.
     * @param user: user with updated state.
     */
    fun disableItem(user: User) {
        val list = userLiveData.value
        val itemInList: User? = list?.find { it.account_id == user.account_id }
        itemInList?.disable = user.disable
        itemInList?.follow = user.follow
        userLiveData.postValue(list)
    }


    /**
     * Calls for user data.
     */
    fun getUserData(siteName: String) {
        repo.getUserData(userLiveData, siteName)
    }
}