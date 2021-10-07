package com.greedygame.imageloader.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.imageloader.R
import com.greedygame.imageloader.helper.adapter.GenericAdapter
import com.greedygame.imageloader.helper.adapter.JavaViewHolderFactory
import com.greedygame.imageloader.helper.listener.OnItemClickListener
import com.greedygame.imageloader.model.photo.Photo

class Adapter(
    list: List<Any>,
    private var listener: OnItemClickListener
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