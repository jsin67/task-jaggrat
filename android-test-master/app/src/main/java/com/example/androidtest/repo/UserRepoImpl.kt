package com.example.androidtest.repo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.androidtest.models.User
import com.example.androidtest.rest.RestApi

class UserRepoImpl(private val restApi: RestApi) : UserRepo {

    /**
     * Call api to get user data.
     * @param data: set user data in this reference
     * @param siteName: api end point name
     */
    @SuppressLint("CheckResult")
    override fun getUserData(data: MutableLiveData<List<User>>, siteName: String) {
        //val data = MutableLiveData<List<User>>()

        restApi.getUserData(siteName)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe({ templates ->
                data.value = templates.body()?.items ?: listOf()
            }, {
                data.value = emptyList()
            })
    }
}