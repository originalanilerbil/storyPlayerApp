package com.anilerbil.storyplayerapp

import com.google.gson.annotations.SerializedName

data class StoryGroup(
    @SerializedName("data")
    val data: List<Stories>
)

data class Stories(
    @SerializedName("stories")
    val storyGroup: List<Story>
)

data class Story(
    @SerializedName("story_id")
    val storyId: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("story_time")
    val storyTime: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("content_type")
    val type: Int,

    @SerializedName("user_pp")
    val userPp: String
)