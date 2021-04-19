package com.wayne.library.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.wayne.library.R


@BindingAdapter("imgRes")
fun ImageView.bindImageRes(resId: Int?) {
    if (resId != null) {
        Glide.with(this.context).load(resId).into(this)
    }
}

@BindingAdapter(value = ["imgUrl", "imgRadius"], requireAll = false)
fun ImageView.bindImageUrl(url: String?, radius: Float = 0F) {
    url?.let {
        if (radius == 0F) {
            Glide.with(this)
                .load(it)
                .transition(GenericTransitionOptions.with(R.anim.fade_in))
                .into(this)
        } else {
            Glide.with(this)
                .load(it)
                .transform(RoundedCorners(radius.toInt()))
                .transition(GenericTransitionOptions.with(R.anim.fade_in))
                .into(this)
        }
    }
}