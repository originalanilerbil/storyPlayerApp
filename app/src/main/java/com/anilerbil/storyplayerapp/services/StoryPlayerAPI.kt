package com.anilerbil.storyplayerapp.services

import com.anilerbil.storyplayerapp.StoryGroup
import io.reactivex.Single
import retrofit2.http.GET

interface StoryPlayerAPI {

    //  https://raw.githubusercontent.com/isiktashamza/StoryPlayer/master/app/src/main/assets/story.json

    @GET("isiktashamza/StoryPlayer/master/app/src/main/assets/story.json")
    fun getStories(): Single<List<StoryGroup>>
}