package com.example.androidtest.rest

import com.example.androidtest.models.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("/users")
    fun getUserData(
        @Query("site") site: String,
        @Query("pagesize") itemCounts: Int = 20,
        @Query("order") orderBy: String = "desc",
        @Query("sort") sort: String = "reputation"
    ): Single<Response<UserResponse>>
}