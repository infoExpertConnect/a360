package com.pawanjes.numbers.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.pawanjes.numbers.service.model.AboutContactBean
import com.pawanjes.numbers.service.model.RetrofitResponse
import com.pawanjes.numbers.service.retrofit.DataRepository

class TextViewModel : ViewModel() {

    private val dataRepository: DataRepository? = DataRepository.instance()


    fun getContactUs(): LiveData<RetrofitResponse<AboutContactBean>> {
        val videoLiveData = MutableLiveData<RetrofitResponse<AboutContactBean>>()
        dataRepository!!.getContactUs().observeForever { videoLiveData.setValue(it) }
        return videoLiveData
    }

    fun getAboutUs(): LiveData<RetrofitResponse<AboutContactBean>> {
        val videoLiveData = MutableLiveData<RetrofitResponse<AboutContactBean>>()
        dataRepository!!.getAboutUs().observeForever { videoLiveData.setValue(it) }
        return videoLiveData
    }
}
