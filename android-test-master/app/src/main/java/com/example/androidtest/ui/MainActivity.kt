package com.example.androidtest.ui

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.di.Injectable
import com.example.androidtest.models.User
import com.example.androidtest.ui.adapter.UserListAdapter
import com.example.androidtest.utils.ConnectivityReceiver
import com.example.androidtest.utils.DATA_KEY
import com.example.androidtest.utils.END_POINT
import com.example.androidtest.utils.verifyAvailableNetwork
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable, OnUserTappedListener,
    ConnectivityReceiver.ConnectivityReceiverListener {
    private val requestResultDetail = 1

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private val adapter = UserListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)
        setupView()
        registerForInternet()

        viewModel.userLiveData.observe(this, Observer { user ->
            if (user != null) {
                adapter.setUserData(user)
            }
            dismissProgressing()
        })

    }

    /**
     * Callback for every change in network connection
     * @param isConnected: true if internet is connected
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            if (viewModel.userLiveData.value.isNullOrEmpty()) {
                fetchUserData()
            } else {
                adapter.setUserData(viewModel.userLiveData.value)
            }
        } else {
            Toast.makeText(this, getString(R.string.internet_warning), Toast.LENGTH_LONG).show()
            adapter.setUserData(emptyList())
        }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onStop() {
        super.onStop()
        ConnectivityReceiver.connectivityReceiverListener = null
    }

    /**
     * Registers for internet change situation
     */
    private fun registerForInternet() {
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }


    /**
     * Sets up views
     */
    private fun setupView() {
        recycler_view.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(it.context, RecyclerView.VERTICAL))
        }
    }

    /**
     * Calls view model data for the update.
     */
    private fun fetchUserData() {
        if (viewModel.userLiveData.value.isNullOrEmpty()) {
            showProgressing()
            viewModel.getUserData(END_POINT)
        }
    }

    /**
     * Show progress
     */
    private fun showProgressing() {
        LoadingDialogFragment().show(supportFragmentManager, LoadingDialogFragment.TAG)
    }

    /**
     * Dismiss progress
     */
    private fun dismissProgressing() {
        supportFragmentManager.findFragmentByTag(LoadingDialogFragment.TAG)
            ?.takeIf { it is DialogFragment }
            ?.run {
                (this as DialogFragment).dismiss()
            }
    }

    /**
     * Callback when user is selected
     * @param user: Selected User
     */
    override fun onItemTapped(user: User) {
        Intent(applicationContext, DetailsActivity::class.java).also {
            it.putExtra(DATA_KEY, user)
            startActivityForResult(it, requestResultDetail)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestResultDetail) {
            if (resultCode == Activity.RESULT_OK) {
                val userInfo = data?.getSerializableExtra(DATA_KEY) as User
                if (!verifyAvailableNetwork(this)) {
                    Toast.makeText(this, getString(R.string.internet_warning), Toast.LENGTH_LONG).show()
                    adapter.setUserData(emptyList())
                } else {
                    viewModel.disableItem(userInfo)
                }
            }
        }
    }
}
