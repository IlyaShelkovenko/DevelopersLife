package com.gmail.hostov47.developerslife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.hostov47.developerslife.extensions.dpToIntPx
import com.gmail.hostov47.developerslife.repositories.RootRepository
import kotlinx.android.synthetic.main.activity_root.*

class RootActivity : AppCompatActivity() {
    private val repository = RootRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        setupToolbar()
        //loadImage(mainImage,"http://static.devli.ru/public/images/gifs/202007/f1e78b48-3bf9-4184-8ec5-471ee80f3f76.gif")
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val logo = if(toolbar.childCount == 2)
            toolbar.getChildAt(1) as? ImageView
        else null
        logo?.scaleType = ImageView.ScaleType.CENTER_CROP
        val lp = logo?.layoutParams as Toolbar.LayoutParams
        lp?.let {
            it.width = this.dpToIntPx(40)
            it.height = this.dpToIntPx(40)
            it.marginEnd = this.dpToIntPx(16)
            logo.layoutParams = it
        }
    }

    fun loadImage(imgView: ImageView, imgUrl: String?) {
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

}
