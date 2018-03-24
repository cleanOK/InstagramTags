package com.dmytrod.instagramtags.data.model

import com.google.gson.annotations.SerializedName

data class Thumbnail(

        @SerializedName("height")
        var height: Long?,
        @SerializedName("url")
        var url: String?,
        @SerializedName("width")
        var width: Long?
)
