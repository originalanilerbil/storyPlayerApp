package com.anilerbil.storyplayerapp.ViewModel

import androidx.fragment.app.Fragment
import com.anilerbil.storyplayerapp.Story

interface ChangeStory {

    var stories: List<Story>
    var currentStory: Int

    fun getCount(): Int{
        return stories.size
    }

    fun getItem(position: Int): Fragment{
        currentStory = 0
        when(position){
            //stories.get(currentStory) -> {  }
        }
    }


}