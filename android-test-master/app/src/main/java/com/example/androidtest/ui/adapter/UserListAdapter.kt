package com.example.androidtest.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.models.User
import com.example.androidtest.ui.OnUserTappedListener
import com.example.androidtest.utils.loadImage

class UserListAdapter(listener: OnUserTappedListener) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private val tappedListener = listener

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: RelativeLayout = itemView.findViewById(R.id.rl_parent)
        val txtName: TextView = itemView.findViewById(R.id.tv_user_name)
        val txtFollowed: TextView = itemView.findViewById(R.id.tv_followed)
        val txtReputation: TextView = itemView.findViewById(R.id.tv_reputation)
        val ivProfileImage: ImageView = itemView.findViewById(R.id.iv_profile_image)
    }

    private val userList = mutableListOf<User>()

    override fun getItemCount(): Int = userList.size

    fun setUserData(list: List<User>?) {
        list?.let {
            userList.clear()
            userList.addAll(list)
            notifyDataSetChanged()
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = userList[position]
        holder.txtName.text = user.display_name
        holder.txtReputation.text = user.reputation.toString()
        holder.loadImage(holder.ivProfileImage, user.profile_image)
        holder.container.setBackgroundColor(Color.WHITE)
        holder.itemView.setOnClickListener { tappedListener.onItemTapped(user) }
        holder.txtFollowed.text = ""

        if (user.disable) {
            holder.txtFollowed.text = "Blocked"
            holder.container.setBackgroundColor(Color.DKGRAY)
            holder.itemView.isEnabled = false
        } else if (user.follow) {
            holder.txtFollowed.text = "Followed"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }
}