package com.dmytrod.instagramtags.data.model

import com.google.gson.annotations.SerializedName

data class Images(
        @SerializedName("low_resolution")
        var lowResolution: LowResolution?,
        @SerializedName("standard_resolution")
        var standardResolution: StandardResolution?,
        @SerializedName("thumbnail")
        var thumbnail: Thumbnail?
)
