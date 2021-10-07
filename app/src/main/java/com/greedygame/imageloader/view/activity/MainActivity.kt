package com.greedygame.imageloader.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.imageloader.R
import com.greedygame.imageloader.helper.adapter.GenericAdapter
import com.greedygame.imageloader.helper.adapter.JavaViewHolderFactory
import com.greedygame.imageloader.helper.constants.Constants
import com.greedygame.imageloader.helper.listener.OnItemClickListener
import com.greedygame.imageloader.model.photo.Photo
import com.greedygame.imageloader.viewmodel.viewmodelfactory.PhotosViewModelFactory
import com.urfeed.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


// List images
class MainActivity : AppCompatActivity(), KodeinAware, OnItemClickListener {

    companion object{
        val TAG = "MainActivity"
    }

    private val page: Int = 1
    private val per_page: Int = 100
    override val kodein by kodein()

    private val viewModelFactory: PhotosViewModelFactory by instance()

    private val viewModel: PhotosViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(PhotosViewModel::class.java)
    }

    private var photos = ArrayList<Photo>()
    private val adapter = Adapter(photos, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        getPhotos()
    }

    private fun initUI() {
        recyclerView.adapter = adapter

        // Set Cache as DiskCache if you are using ImageLoader2
//        ImageLoader2.setCache(DiskCache(applicationContext))
    }

    private fun getPhotos() {
        viewModel.getPhotos(page, per_page).observe(this, Observer {
            photos.addAll(it)
            adapter.notifyItemRangeChanged(0, photos.size)
        })
    }

    override fun onItemClick(view: View, position: Int, data: Any?) {
        val intent = Intent(this, FullscreenActivity::class.java).apply {
            val photo = data as Photo
            putExtra(Constants.KEY_PHOTO, photo.urls.regular)
        }
        startActivity(intent)
    }


    class Adapter(
        list: List<Any>,
        var listener: OnItemClickListener
    ) : GenericAdapter<Any>(list) {
        override fun getLayoutId(position: Int, obj: Any?): Int {
            return when (obj) {
                is Photo -> R.layout.item_photo
                else -> error("Unknown type: for position: $position")
            }
        }

        override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            return JavaViewHolderFactory.create(view, viewType, listener)
        }
    }

}