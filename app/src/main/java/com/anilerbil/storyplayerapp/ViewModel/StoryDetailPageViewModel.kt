package com.anilerbil.storyplayerapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anilerbil.storyplayerapp.Stories
import com.anilerbil.storyplayerapp.StoryGroup


class StoryDetailPageViewModel: ViewModel() {

    interface changeStoryGroup {
        fun nextStoryGroup()
        fun previousStoryGroup()
    }
}