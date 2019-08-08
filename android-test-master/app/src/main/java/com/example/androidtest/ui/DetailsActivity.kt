package com.example.androidtest.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.androidtest.R
import com.example.androidtest.models.User
import com.example.androidtest.utils.DATA_KEY
import com.example.androidtest.utils.convertLongToTime

/**
 * Handles block, unblock and rendering of the user information
 */
class DetailsActivity : AppCompatActivity() {
    private var mUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if(intent.hasExtra(DATA_KEY)) {
            mUser = intent?.getSerializableExtra(DATA_KEY) as User
            mUser.let {
                setUpViewsWithInfo(mUser)
                setFollowOrBlock(mUser)
                setProfileImage(mUser)
            }
        }


    }

    /**
     * Sets profile pic for user
     * @param user: User info
     */
    private fun setProfileImage(user: User?) {
        user?.profile_image.let {
            val profileImage = findViewById<ImageView>(R.id.iv_profile_image)
            val placeholder = ColorDrawable(Color.WHITE)
            val requestOptions = RequestOptions
                .placeholderOf(placeholder)
                .fitCenter()
                .optionalCenterCrop()

            Glide.with(this)
                .load(it)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(profileImage)
        }

    }

    /**
     * Renders user information
     * @param user: User info
     */
    private fun setUpViewsWithInfo(user: User?) {
        user?.let {
            findViewById<TextView>(R.id.tv_id).text = String.format(getString(R.string.account_id), it.account_id)
            findViewById<TextView>(R.id.tv_name).text = String.format(getString(R.string.display_name), it.display_name)
            findViewById<TextView>(R.id.tv_reputation).text =
                String.format(getString(R.string.reputation), it.reputation)
            findViewById<TextView>(R.id.tv_location).text = String.format((getString(R.string.location)), it.location)

        }
        findViewById<Button>(R.id.bt_done).setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(DATA_KEY, mUser)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        findViewById<Button>(R.id.bt_block).setOnClickListener {
            user?.disable = true
            user?.follow = false
            setFollowOrBlock(user)
        }

        findViewById<Button>(R.id.bt_follow).setOnClickListener {
            user?.disable = false
            user?.follow = true
            setFollowOrBlock(user)
        }
        user?.creation_date?.let {
            findViewById<TextView>(R.id.tv_date).text = String.format(getString(R.string.date), convertLongToTime(it))
        }
    }

    /**
     * Updates ui as per new state
     * @param user: New state of user data
     */
    private fun setFollowOrBlock(user: User?) {
        user?.let {
            val followOrBlock = findViewById<TextView>(R.id.tv_follow)
            if (it.follow) {
                followOrBlock.text = String.format(getString(R.string.status), getString(R.string.following))
            } else if (it.disable) {
                followOrBlock.text = String.format(getString(R.string.status), getString(R.string.block))
            } else {
                followOrBlock.text = String.format(getString(R.string.status), getString(R.string.choose_an_options))
            }
        }
    }
}
