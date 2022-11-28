package com.anilerbil.storyplayerapp.ViewModel

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anilerbil.storyplayerapp.StoryGroup
import com.anilerbil.storyplayerapp.services.StoryPlayerAPI
import com.anilerbil.storyplayerapp.services.StoryPlayerAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainPageViewModel: ViewModel() {

    var storyGroup = MutableLiveData<List<StoryGroup>>()
    var isDataLoad = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<Boolean>()

    private val storyplayerApiService = StoryPlayerAPIService()
    private val disposable = CompositeDisposable()


    fun getDatafromInt() {
        isDataLoad.value = true
        disposable.add(
            storyplayerApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<StoryGroup>>(){
                    override fun onSuccess(t: List<StoryGroup>) {
                        storyGroup.value = t
                        isDataLoad.value = true
                        errorMessage.value = false
                    }

                    override fun onError(e: Throwable) {
                        isDataLoad.value = false
                        errorMessage.value = true
                        e.printStackTrace()
                    }
                }
                )
        )
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}