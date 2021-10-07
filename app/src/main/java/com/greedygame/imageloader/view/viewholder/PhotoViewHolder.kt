package com.greedygame.imageloader.view.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.imageloader.memorycache.ImageLoader
import com.greedygame.imageloader.R
import com.greedygame.imageloader.diskcache.ImageLoader2
import com.greedygame.imageloader.helper.adapter.GenericAdapter
import com.greedygame.imageloader.helper.listener.OnItemClickListener
import com.greedygame.imageloader.model.photo.Photo


class PhotoViewHolder(
    itemView: View,
    listener: OnItemClickListener?
) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<Photo> {

    var imageView: ImageView
    var listener: OnItemClickListener? = null

    init {
        imageView = itemView.findViewById(R.id.imageView)
        this.listener = listener
    }


    override fun bind(data: Photo) {

//        1. Using ImageLoader which uses MemoryCache.
        ImageLoader.displayImage(data.urls.thumb, imageView)


//        2. Using ImageLoader2 which uses DiskCache.
//        ImageLoader2.displayImage(itemView.context, data.urls.thumb, imageView)

//        3. For cancelling ongoing requests, you can call cancelAll()
//        ImageLoader.cancelAll()

        imageView.setOnClickListener {
            listener?.onItemClick(
                it,
                adapterPosition,
                data
            )
        }
    }
}
