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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }
}
