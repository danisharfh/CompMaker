package com.example.caripartner.data

import com.example.caripartner.data.Constants.Constants
import com.example.caripartner.ui.NotificationApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {


    companion object{
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(NotificationApi::class.java)
        }
    }
}