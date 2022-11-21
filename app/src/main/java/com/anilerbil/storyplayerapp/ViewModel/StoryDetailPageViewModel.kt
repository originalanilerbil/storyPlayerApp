/*package com.anilerbil.storyplayerapp.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.anilerbil.storyplayerapp.Stories
import com.anilerbil.storyplayerapp.services.StoryPlayerAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class StoryDetailPageViewModel: ViewModel() {

    var stories = MutableLiveData<List<Stories>>()
    var isDataLoad2 = MutableLiveData<Boolean>()
    var errorMessage2 = MutableLiveData<Boolean>()

    private val storyplayerApiService2 = StoryPlayerAPIService()
    private val disposable2 = CompositeDisposable()


    private fun getDatafromInt2()  {
        isDataLoad2.value = true
        disposable2.add(
            storyplayerApiService2.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<>){

                }
        )
    }

} */