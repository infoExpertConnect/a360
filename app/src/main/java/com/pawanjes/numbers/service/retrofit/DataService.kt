package com.pawanjes.numbers.service.retrofit

import com.pawanjes.numbers.service.model.AboutContactBean
import com.pawanjes.numbers.service.model.RetrofitResponse
import retrofit2.Call
import retrofit2.http.GET

interface DataService {

    @GET("/api/page/contactus")
    fun getContactUs(): Call<RetrofitResponse<AboutContactBean>>

    @GET("api/page/aboutus")
    fun getAboutUs(): Call<RetrofitResponse<AboutContactBean>>

}

