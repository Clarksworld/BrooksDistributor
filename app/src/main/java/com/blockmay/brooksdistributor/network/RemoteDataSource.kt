package com.blockmay.brooksdistributor.network

import android.util.Base64
import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {


    companion object{


        private val AUTH = "Basic " + Base64.encodeToString(
            "afreputility@gmail.com:Mth112@eng".toByteArray(),
            Base64.NO_WRAP
        )

//        7016634302


//        private const val BASE_URL = " https://sandbox.vtpass.com/"

        private const val BASE_URL = "https://vtpass.com/"

//        private const val ASSET_BASE_URL = "http://147.182.200.74/"

//        private const val DEV_BASE = "https://dev.utility.afrep.io/"

        private const val MAIN_BASE = "https://utility.afrep.io/"
        private const val ASSET_BASE_URL = "https://api.thebrookwaters.com/"



    }


    fun <Api>userLogin(
        api: Class<Api>,
    ): Api{
        return Retrofit.Builder()
            .baseUrl(ASSET_BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Content-Type", "multipart/form-data")
                    }.build())
                }.also {client ->

                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }


    fun <Api>userDetails(
        api: Class<Api>,
        authToken: String? = null
    ): Api{

        return Retrofit.Builder()
            .baseUrl(ASSET_BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Authorization", "Bearer $authToken")
                    }.build())
                }.also {client ->

                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}