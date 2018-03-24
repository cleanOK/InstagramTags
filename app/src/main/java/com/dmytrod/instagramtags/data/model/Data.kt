package com.dmytrod.instagramtags.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dmytro Denysenko on 26.01.18.
 */
data class Data<out T>(@SerializedName("data") val data: T?)