package com.urfeed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.greedygame.imageloader.data.repository.PhotosRepository
import com.greedygame.imageloader.model.photo.Photo

class PhotosViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    fun getPhotos(page: Int, per_page: Int): LiveData<List<Photo>> {
        return photosRepository.getPhotos(page, per_page)
    }

}