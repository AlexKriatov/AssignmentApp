package com.kriatov.alex.assignmentapp.network.model

/**
 * Created by Alex Kriatov on 12/21/18
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Title {

    @SerializedName("value")
    @Expose
    var value: String? = null
    @SerializedName("matchLevel")
    @Expose
    var matchLevel: String? = null
    @SerializedName("matchedWords")
    @Expose
    var matchedWords: List<Any>? = null

}