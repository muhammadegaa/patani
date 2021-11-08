package com.fluid.smartcrops

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object{
        val BASE_URL = "http://17076013-10a5-4182-93af-a0ec7f7bfbee.southeastasia.azurecontainer.io/"
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
        }.build()

        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
        }

    }
}