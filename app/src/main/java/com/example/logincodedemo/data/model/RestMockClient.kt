package com.example.logincodedemo.data.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RestMockClient {
    private var mRestService: IRestService? = null

    fun getClient(): IRestService? {
        if (mRestService == null) {
            val client = OkHttpClient.Builder().addInterceptor(MokInterceptor()).build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())

                    .baseUrl(IRestService.ENDPOINT).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
            mRestService = retrofit.create(IRestService::class.java)
        }
        return mRestService
    }
}