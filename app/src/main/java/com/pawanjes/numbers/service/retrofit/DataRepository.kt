package com.pawanjes.numbers.service.retrofit

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.pawanjes.numbers.service.model.AboutContactBean
import com.pawanjes.numbers.service.model.BannersResponse
import com.pawanjes.numbers.service.model.LeadResponse
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

    fun getMainLeads(): LiveData<RetrofitResponse<ArrayList<LeadResponse>>> {
        val leadData = MutableLiveData<RetrofitResponse<ArrayList<LeadResponse>>>()
        val call = dataService.getMainLeads()
        call.enqueue(object : Callback<RetrofitResponse<ArrayList<LeadResponse>>> {
            override fun onResponse(call: Call<RetrofitResponse<ArrayList<LeadResponse>>>, response: Response<RetrofitResponse<ArrayList<LeadResponse>>>) {
                leadData.value = response.body()

            }
            override fun onFailure(call: Call<RetrofitResponse<ArrayList<LeadResponse>>>, t: Throwable) {
                leadData.value = null
            }
        })
        return leadData

    }

    fun getBanners(): LiveData<RetrofitResponse<BannersResponse>> {
        val bannerData = MutableLiveData<RetrofitResponse<BannersResponse>>()
        val call = dataService.getBanners()
        call.enqueue(object : Callback<RetrofitResponse<BannersResponse>> {
            override fun onResponse(call: Call<RetrofitResponse<BannersResponse>>, response: Response<RetrofitResponse<BannersResponse>>) {
                bannerData.value = response.body()

            }
            override fun onFailure(call: Call<RetrofitResponse<BannersResponse>>, t: Throwable) {
                bannerData.value = null
            }
        })
        return bannerData

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

