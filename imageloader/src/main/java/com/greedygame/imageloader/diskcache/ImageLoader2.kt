package com.greedygame.imageloader.diskcache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.greedygame.imageloader.ImageCache
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

object ImageLoader2 {
    private lateinit var cache: ImageCache
    private var executorService: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val uiHandler: Handler = Handler(Looper.getMainLooper())
    private var future: Future<Any>? = null

    fun displayImage(context: Context, url: String, imageView: ImageView) {
        cache = DiskCache(context)
        val cached = cache.get(url)
        if (cached != null) {
            updateImageView(imageView, cached)
            return
        }
        imageView.tag = url

        future = executorService.submit(Callable {
            val bitmap: Bitmap? = downloadImage(url)
            if (bitmap != null) {
                if (imageView.tag == url) {
                    updateImageView(imageView, bitmap)
                }
                cache.put(url, bitmap)
            }

        })
    }

    fun clearCache() {
        cache.clear()
    }

    private fun updateImageView(imageView: ImageView, bitmap: Bitmap) {
        uiHandler.post {
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun downloadImage(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL(url)
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            bitmap = BitmapFactory.decodeStream(conn.inputStream)
            conn.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    fun cancelAll(){
        future?.cancel(true)
    }
}