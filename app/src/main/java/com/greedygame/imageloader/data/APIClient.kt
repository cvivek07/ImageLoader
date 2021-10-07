package com.greedygame.imageloader.data

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


class APIClient() {

    private var retrofit: Retrofit? = null

    private var apiCalls: APICalls? = null

    fun getClient(): APICalls? {

        if (apiCalls == null) {

            val client = OkHttpClient
                .Builder()
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build()


            retrofit = Retrofit.Builder()
                .baseUrl(APIConst.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            apiCalls = retrofit?.create(APICalls::class.java)!!
        }

        return apiCalls
    }

}