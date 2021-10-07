package com.greedygame.imageloader.data.repository

import com.greedygame.imageloader.data.APIClient
import com.greedygame.imageloader.data.APIConst
import com.greedygame.imageloader.helper.listener.OnResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class BaseRepository(
    private val apiClient: APIClient
) {

    fun callAPI(
        url: String,
        requestType: Int,
        params: HashMap<String, Any?>,
        onResponse: OnResponse
    ) {

        var call: Call<String>? = null

        if (APIConst.GET == requestType) {
            call = apiClient.getClient()?.getRequest(url, params)
        } else {
            // other calls like POST, PUT, DELETE
        }

        call?.enqueue(object : Callback<String> {

            override fun onFailure(call: Call<String>, t: Throwable) {
                onResponse.onFailure(call, t)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    if (response.isSuccessful) {
                        onResponse.onSuccess(response.code(), response.body().toString())
                    } else {
                        if (response.code() == 402) {

                        } else {
                            onResponse.onSuccess(response.code(), response.errorBody().toString())
                        }

                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }

            }

        })
    }



}