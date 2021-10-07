package com.greedygame.imageloader.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.greedygame.imageloader.BuildConfig
import com.greedygame.imageloader.data.APIClient
import com.greedygame.imageloader.data.APIConst
import com.greedygame.imageloader.helper.listener.OnResponse
import com.greedygame.imageloader.model.photo.Photo
import retrofit2.Call


class PhotosRepository(
    private val apiClient: APIClient
) : BaseRepository(apiClient) {

    fun getPhotos(page: Int, per_page: Int): LiveData<List<Photo>> {

        var data = MutableLiveData<List<Photo>>()

        val param = HashMap<String, Any?>().apply {
            put("client_id", BuildConfig.UNSPLASH_CLIENT_ID)
            put("page", page)
            put("per_page", per_page)
        }

        callAPI(
            APIConst.photos,
            APIConst.GET,
            param,
            object : OnResponse {

                override fun onSuccess(code: Int, response: String) {

                    try {
                        val phototype = object : TypeToken<List<Photo>>() {}.type
                        val photos = Gson().fromJson<List<Photo>>(response, phototype)
                        data.value = photos
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    data.value =  null
                }

                override fun onNoInternet() {

                }

            })

        return data
    }


}