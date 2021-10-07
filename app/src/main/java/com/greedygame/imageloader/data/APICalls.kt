package com.greedygame.imageloader.data


import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.*

interface APICalls {

    @GET
    fun getRequest(
        @Url url: String,
        @QueryMap query: HashMap<String, Any?>
    ): Call<String>
}