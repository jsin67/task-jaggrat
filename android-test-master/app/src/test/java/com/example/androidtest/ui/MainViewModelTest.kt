package com.example.androidtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.androidtest.models.User
import com.example.androidtest.repo.UserRepo
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var repo: UserRepo
    private lateinit var viewModel : MainViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repo = mock()
        viewModel = MainViewModel(repo)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testUpdateUserForBlock(){
        val user = User(1, "jaggrat", "", 1, 123434511, "London", false, false)
        val user2 = User(2, "jaggrat singh", "", 2, 123434511, "Delhi", false, false)
        val user3 = User(3, "bla bla", "", 3, 123434511, "New York", false, false)
        val user4 = User(4, "ha ha ha ", "", 4, 123434511, "London", false, false)
        viewModel.userLiveData = MutableLiveData(arrayListOf(user, user2, user3, user4))

        user.disable = true
        viewModel.disableItem(user)

        assertEquals(viewModel.userLiveData.value?.get(0)?.account_id, user.account_id)
        assertEquals(viewModel.userLiveData.value?.get(0)?.disable, true)
    }


    @Test
    fun testUpdateUserForFollow(){
        val user = User(1, "jaggrat", "", 1, 123434511, "London", false, false)
        val user2 = User(2, "jaggrat singh", "", 2, 123434511, "Delhi", false, false)
        val user3 = User(3, "bla bla", "", 3, 123434511, "New York", false, false)
        val user4 = User(4, "ha ha ha ", "", 4, 123434511, "London", false, false)
        viewModel.userLiveData = MutableLiveData(arrayListOf(user, user2, user3, user4))

        user3.follow = true
        viewModel.disableItem(user)

        assertEquals(viewModel.userLiveData.value?.get(2)?.account_id, user3.account_id)
        assertEquals(viewModel.userLiveData.value?.get(2)?.follow, true)
        assertEquals(viewModel.userLiveData.value?.get(2)?.disable, false)
    }

    @Test
    fun testUpdateUserCountAfterDisable(){
        val user = User(1, "jaggrat", "", 1, 123434511, "London", false, false)
        val user2 = User(2, "jaggrat singh", "", 2, 123434511, "Delhi", false, false)
        val user3 = User(3, "bla bla", "", 3, 123434511, "New York", false, false)
        val user4 = User(4, "ha ha ha ", "", 4, 123434511, "London", false, false)
        viewModel.userLiveData = MutableLiveData(arrayListOf(user, user2, user3, user4))

        user3.follow = true
        viewModel.disableItem(user)

        assertEquals(viewModel.userLiveData.value?.size, 4)
    }

    @Test
    fun testUpdateWhenThereNoUserInList(){
        val user = User(1, "jaggrat", "", 1, 123434511, "London", false, false)
//        val user2 = User(2, "jaggrat singh", "", 2, 123434511, "Delhi", false, false)
//        val user3 = User(3, "bla bla", "", 3, 123434511, "New York", false, false)
//        val user4 = User(4, "ha ha ha ", "", 4, 123434511, "London", false, false)
//        viewModel.userLiveData = MutableLiveData(arrayListOf(user, user2, user3, user4))
        viewModel.userLiveData = MutableLiveData(arrayListOf())
        user.follow = true
        viewModel.disableItem(user)

        assertEquals(viewModel.userLiveData.value?.size, 0)
    }
}