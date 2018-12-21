package com.kriatov.alex.assignmentapp.network

import com.kriatov.alex.assignmentapp.network.model.ModelConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alex Kriatov on 12/21/18
 */

interface Api {

    @GET("/api/v1/search_by_date")
    fun loadPage(@Query("tags") tags: String = "story", @Query("page") page: Int): Single<ModelConfig>
}