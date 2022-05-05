package com.metromart.repositoriesapp.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.metromart.repositoriesapp.R

fun ImageView.roundedImage(
    url: String?,
    @DrawableRes placeholder: Int = R.drawable.image_github_logo,
    @DrawableRes error: Int = R.drawable.image_github_logo,
) {
    val requestOptions = RequestOptions()
        .circleCrop()
        .placeholder(ContextCompat.getDrawable(this.context, placeholder))
        .error(ContextCompat.getDrawable(this.context, error))

    Glide.with(this.context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}