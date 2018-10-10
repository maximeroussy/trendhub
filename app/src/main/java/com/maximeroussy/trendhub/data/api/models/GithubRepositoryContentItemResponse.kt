package com.maximeroussy.trendhub.data.api.models

import com.google.gson.annotations.SerializedName

data class GithubRepositoryContentItemResponse(
    @SerializedName("type") val type: String,
    @SerializedName("size") val size: Int,
    @SerializedName("name") val name: String,
    @SerializedName("path") val path: String
)
