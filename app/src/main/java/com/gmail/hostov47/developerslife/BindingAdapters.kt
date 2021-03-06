/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.gmail.hostov47.developerslife

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.hostov47.developerslife.overview.DevLifeApiStatus

@BindingAdapter("devLifeApiStatus")
fun bindStatus(statusImageView: ImageView, status: DevLifeApiStatus?) {
    when (status) {
        DevLifeApiStatus.LOADING
        -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        DevLifeApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DevLifeApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("devLifeApiImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("devLifeApiStatusText")
fun bindStatusText(textView : TextView,  status: DevLifeApiStatus?) {
    when (status) {
        DevLifeApiStatus.LOADING,
        DevLifeApiStatus.DONE -> {
            textView.visibility = View.GONE
        }
        DevLifeApiStatus.ERROR -> {
            textView.visibility = View.VISIBLE
        }
    }
}
@BindingAdapter("devLifeApiStatusButton")
fun bindStatusButton(button : Button,  status: DevLifeApiStatus?) {
    when (status) {
        DevLifeApiStatus.LOADING,
        DevLifeApiStatus.DONE -> {
            button.visibility = View.GONE
        }
        DevLifeApiStatus.ERROR -> {
            button.visibility = View.VISIBLE
        }
    }
}
