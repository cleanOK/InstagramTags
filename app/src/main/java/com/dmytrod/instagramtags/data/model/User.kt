package com.dmytrod.instagramtags.data.model

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("full_name")
        var fullName: String?,
        @SerializedName("id")
        var id: String?,
        @SerializedName("profile_picture")
        var profilePicture: String?,
        @SerializedName("username")
        var username: String?
)