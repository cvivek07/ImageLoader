package com.greedygame.imageloader.application

import android.app.Application
import com.greedygame.imageloader.data.APIClient
import com.greedygame.imageloader.data.repository.PhotosRepository
import com.greedygame.imageloader.helper.extension.bindViewModel
import com.greedygame.imageloader.viewmodel.viewmodelfactory.PhotosViewModelFactory
import com.urfeed.viewmodel.PhotosViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class GreedyGameApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@GreedyGameApp))
        bind() from singleton { APIClient() }
        bind() from singleton { PhotosRepository(instance()) }
        bind() from provider { PhotosViewModelFactory(instance()) }
        bindViewModel<PhotosViewModel>() with provider { PhotosViewModel(instance()) }

    }



    override fun onCreate() {
        super.onCreate()
    }


}