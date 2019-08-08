package com.example.androidtest.models

import java.io.Serializable


data class User(
    val account_id: Long,
    val display_name: String,
    val profile_image: String,
    val reputation: Long,
    val creation_date: Long,
    val location: String,
    var disable: Boolean = true,
    var follow: Boolean = false
) : Serializable