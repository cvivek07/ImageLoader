package com.greedygame.imageloader.helper.listener

import org.json.JSONArray
import retrofit2.Call

interface OnResponse {
    fun onSuccess(code: Int, response: String)
    fun onFailure(call: Call<String>, t: Throwable)
    fun onNoInternet()
}