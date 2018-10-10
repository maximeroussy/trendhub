package com.maximeroussy.trendhub.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
  view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
  val context = imageView.context
  Glide.with(context).load(url).into(imageView)
}
