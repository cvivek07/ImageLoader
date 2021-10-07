package com.greedygame.imageloader.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.greedygame.imageloader.memorycache.ImageLoader
import com.greedygame.imageloader.R
import com.greedygame.imageloader.diskcache.ImageLoader2
import com.greedygame.imageloader.helper.constants.Constants
import kotlinx.android.synthetic.main.activity_fullscreen.*

// to display image full screen
class FullscreenActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        getIntentExtras()
        initUI()
    }

    private fun initUI() {
        backButton.setOnClickListener(this)
    }

    private fun getIntentExtras() {
        val photourl = intent?.getStringExtra(Constants.KEY_PHOTO)

//        Using ImageLoader2 here to demo the use of DiskCache.
//        Alternatively, you can also use ImageLoader which uses MemoryCache.
        photourl?.let { ImageLoader2.displayImage(applicationContext, it, fullImageView) }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.backButton -> finish()
        }
    }
}