package com.kriatov.alex.assignmentapp.network.model

/**
 * Created by Alex Kriatov on 12/21/18
 */

import com.google.gson.annotations.SerializedName

data class ModelConfig(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("nbHits")
    val nbhits: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("nbPages")
    val nbpages: Int,
    @SerializedName("hitsPerPage")
    val hitsperpage: Int,
    @SerializedName("processingTimeMS")
    val processingtimems: Int,
    @SerializedName("exhaustiveNbHits")
    val exhaustivenbhits: Boolean,
    @SerializedName("query")
    val query: String,
    @SerializedName("params")
    val params: String
)