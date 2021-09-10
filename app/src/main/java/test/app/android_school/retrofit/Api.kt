package test.app.android_school.retrofit

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.app.android_school.BuildConfig
import test.app.android_school.dagger.ApplicationScope
import javax.inject.Scope

@Module
object Api {

    private const val BASE_URL = BuildConfig.API_URL

    @ApplicationScope
    @Provides
    fun getApiClient(): ApiInterface {
        return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiInterface::class.java)
    }
}