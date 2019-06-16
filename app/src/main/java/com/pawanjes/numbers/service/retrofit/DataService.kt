package com.pawanjes.numbers.service.retrofit

import com.pawanjes.numbers.service.model.AboutContactBean
import com.pawanjes.numbers.service.model.BannersResponse
import com.pawanjes.numbers.service.model.LeadResponse
import com.pawanjes.numbers.service.model.RetrofitResponse
import retrofit2.Call
import retrofit2.http.GET

interface DataService {

    @GET("/api/page/contactus")
    fun getContactUs(): Call<RetrofitResponse<AboutContactBean>>

    @GET("api/page/aboutus")
    fun getAboutUs(): Call<RetrofitResponse<AboutContactBean>>

    @GET("api/leads")
    fun getMainLeads(): Call<RetrofitResponse<ArrayList<LeadResponse>>>

    @GET("api/banners/homepage")
    fun getBanners(): Call<RetrofitResponse<BannersResponse>>
}

