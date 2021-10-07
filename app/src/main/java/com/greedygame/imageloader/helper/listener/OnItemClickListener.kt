package com.greedygame.imageloader.helper.listener

import android.view.View

interface OnItemClickListener {
    fun onItemClick(view: View, position: Int = -1, data: Any? = null)
}