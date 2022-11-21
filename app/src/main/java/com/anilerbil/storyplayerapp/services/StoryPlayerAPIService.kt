package com.anilerbil.storyplayerapp.services

import com.anilerbil.storyplayerapp.StoryGroup
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StoryPlayerAPIService {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(StoryPlayerAPI::class.java)

    fun getData(): Single<List<StoryGroup>>{
       return api.getStories()
    }
}