# ImageLoader

This app has two modules as given in problem statement:
 - app      // sample app to demo the ImageLoading library
 - imageloader // Image loading library written by me.


 ## imageloader module:

 The imageloader module contains the classes related to the code used for developing the library.
 It has two packages:

 1. memorycache - contains the class ImageLoader which uses MemoryCache class for the implementation
 2. diskcache - contains the class ImageLoader2 which uses DiskCache class for the implementation

 Note: DiskCache uses a class DiskLruCache from the Jake wharton's lib 'com.jakewharton:disklrucache:2.0.2'

 ## app module:

 The app module follows a basic MVVM architecture.

 1. application - Custom Application class for dependency injection using kodein

 2. data - repository classes, retrofit api client

 3. helper - contains recylerview adapter which uses a generic pattern for creating adapter. The advantage of
 using this class is that we do not need to create separate adapter class for each recylerview.
 Also, this module has constants, kotlin extension and listener interfaces.

 4. model - entity classes for the data

 5. view - activity, adapter and viewholder

 6. viewmodel - Viewmodel for communicating with repo layers.


## The main picture:

 The imageloader library is used in PhotoViewHolder class.imageloader

//        1. Using ImageLoader which uses MemoryCache.
            ImageLoader.displayImage(data.urls.thumb, imageView)


//        2. Using ImageLoader2 which uses DiskCache.
            ImageLoader2.displayImage(itemView.context, data.urls.thumb, imageView)

//        3. For cancelling ongoing requests, you can call cancelAll().
            ImageLoader.cancelAll()


 In fullscreenActivity, I have used ImageLoader2 which uses DiskCache

      ImageLoader2.displayImage(applicationContext, it, fullImageView)



