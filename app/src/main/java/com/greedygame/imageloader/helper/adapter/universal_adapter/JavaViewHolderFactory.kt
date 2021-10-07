package com.greedygame.imageloader.helper.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.greedygame.imageloader.R
import com.greedygame.imageloader.helper.listener.OnItemClickListener
import com.greedygame.imageloader.view.viewholder.PhotoViewHolder


object JavaViewHolderFactory {

    fun create(view: View, viewType: Int, listener: OnItemClickListener): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_photo -> PhotoViewHolder(view, listener)
            else -> GenericViewHolder(view)
        }
    }

}