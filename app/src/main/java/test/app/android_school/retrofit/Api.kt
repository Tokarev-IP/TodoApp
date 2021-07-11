package test.app.android_school.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    const val BASE_URL = "https://d5dps3h13rv6902lp5c8.apigw.yandexcloud.net."

    val apiClient: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return@lazy retrofit.create(ApiInterface::class.java)
    }
}