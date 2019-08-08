package com.example.androidtest.ui

import com.example.androidtest.models.User

interface OnUserTappedListener {
    fun onItemTapped(user: User)
}