package com.pawanjes.numbers.service.retrofit

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.pawanjes.numbers.service.model.AboutContactBean
import com.pawanjes.numbers.service.model.RetrofitResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository private constructor() {

    private val dataService: DataService = RetrofitBuilder.instance!!.create(DataService::class.java)


    fun getContactUs(): LiveData<RetrofitResponse<AboutContactBean>> {
        val contactusDetail = MutableLiveData<RetrofitResponse<AboutContactBean>>()
        val call = dataService.getContactUs()
        call.enqueue(object : Callback<RetrofitResponse<AboutContactBean>> {
            override fun onFailure(call: Call<RetrofitResponse<AboutContactBean>>, t: Throwable) {
                contactusDetail.value = null
            }

            override fun onResponse(
                call: Call<RetrofitResponse<AboutContactBean>>, response: Response<RetrofitResponse<AboutContactBean>>
            ) {
                contactusDetail.value = response.body()
            }

        })
        return contactusDetail

    }

    fun getAboutUs(): LiveData<RetrofitResponse<AboutContactBean>> {
        val contactusDetail = MutableLiveData<RetrofitResponse<AboutContactBean>>()
        val call = dataService.getAboutUs()
        call.enqueue(object : Callback<RetrofitResponse<AboutContactBean>> {
            override fun onFailure(call: Call<RetrofitResponse<AboutContactBean>>, t: Throwable) {
                contactusDetail.value = null
            }

            override fun onResponse(
                call: Call<RetrofitResponse<AboutContactBean>>, response: Response<RetrofitResponse<AboutContactBean>>
            ) {
                contactusDetail.value = response.body()
            }

        })
        return contactusDetail

    }

    companion object {
        private var dataRepository: DataRepository? = null

        fun instance(): DataRepository? {
            if (dataRepository == null) {
                dataRepository = DataRepository()
            }
            return dataRepository
        }
    }
}

