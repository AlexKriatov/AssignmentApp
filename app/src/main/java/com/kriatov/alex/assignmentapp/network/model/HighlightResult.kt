package com.kriatov.alex.assignmentapp.network.model

/**
 * Created by Alex Kriatov on 12/21/18
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HighlightResult {

    @SerializedName("title")
    @Expose
    var title: Title? = null
    @SerializedName("url")
    @Expose
    var url: Url? = null
    @SerializedName("author")
    @Expose
    var author: Author? = null
    @SerializedName("story_text")
    @Expose
    var storyText: StoryText? = null

}