package com.pawanjes.numbers.service.retrofit

import com.pawanjes.numbers.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {


    //private static Retrofit customBaseUrlRetrofit = null;

    private var retrofit: Retrofit? = null
    val instance: Retrofit?
        get() {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor)
                    .addInterceptor(BasicAuthInterceptor(BuildConfig.USER_NAME, BuildConfig.PASSWORD))
                    .build()

                val builder = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                if (BuildConfig.DEBUG) {
                    builder.client(client)
                }

                retrofit = builder.build()
            }
            return retrofit
        }


    fun getInstance(baseUrl: String): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(0, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.SECONDS)
            .readTimeout(0, TimeUnit.SECONDS)
            .addInterceptor(BasicAuthInterceptor(BuildConfig.USER_NAME, BuildConfig.PASSWORD))
            .build()

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
        if (BuildConfig.DEBUG) {
            builder.client(client)
        }
        return builder.build()


    }

}
