package com.dmytrod.instagramtags.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmytro Denysenko on 23.01.18.
 */
data class Tag(@SerializedName("name") val name: String,
               @SerializedName("media_count") val mediaCount: Int) {
    override fun toString(): String {
        return name
    }
}