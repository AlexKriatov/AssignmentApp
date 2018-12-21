package com.kriatov.alex.assignmentapp.core

import android.app.Application
import com.kriatov.alex.assignmentapp.BuildConfig
import com.kriatov.alex.assignmentapp.network.Api
import com.kriatov.alex.assignmentapp.network.RestClient

/**
 * Created by Alex Kriatov on 12/21/18
 */
class App : Application(), ApiProvider {

    private lateinit var api: Api

    override fun onCreate() {
        super.onCreate()
        api = RestClient.getInstance.createRetrofit(BuildConfig.BASE_URL).create(Api::class.java)

    }

    override fun getApi(): Api {
        return api
    }
}