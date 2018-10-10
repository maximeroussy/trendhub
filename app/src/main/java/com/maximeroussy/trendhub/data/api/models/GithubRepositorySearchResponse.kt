package com.maximeroussy.trendhub.data.api.models

import com.google.gson.annotations.SerializedName

data class GithubRepositorySearchResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val items: List<GithubRepositoryResponse>
)
