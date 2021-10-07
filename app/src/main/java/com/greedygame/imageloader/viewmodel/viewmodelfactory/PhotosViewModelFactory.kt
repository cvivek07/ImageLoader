package com.greedygame.imageloader.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greedygame.imageloader.data.repository.PhotosRepository
import com.urfeed.viewmodel.PhotosViewModel

class PhotosViewModelFactory(private val photosRepository: PhotosRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosViewModel(photosRepository) as T
    }
}