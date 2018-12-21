package com.kriatov.alex.assignmentapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kriatov.alex.assignmentapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Alex Kriatov on 12/21/18
 */
class RestClient {
    private var gson = createGson()

    companion object {
        var getInstance = RestClient()
    }

    private fun createGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss")
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }

    private fun createHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addNetworkInterceptor(createLogger())

        okHttpClientBuilder.readTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.connectTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.writeTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.retryOnConnectionFailure(true)

        okHttpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("SerialResponse-Type", "application/x-www-form-urlencoded")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return okHttpClientBuilder.build()
    }

    private fun createLogger(): Interceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    fun getGson(): Gson {
        return gson
    }

    fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(createHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }
}