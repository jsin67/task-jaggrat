package com.example.androidtest.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ViewHolder.loadImage(imageView: ImageView, url: String?) {
    val placeholder = ColorDrawable(Color.WHITE)
    val requestOptions = RequestOptions
        .placeholderOf(placeholder)
        .fitCenter()
        .optionalCenterCrop()

    Glide.with(itemView.context)
        .load(url)
        .apply(requestOptions)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}


