package com.maximeroussy.trendhub.data.api.models

import com.google.gson.annotations.SerializedName

data class GithubRepositoryResponse(
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("language") val language: String?,
    @SerializedName("forks_count") val forksCount: Int
) {

  data class Owner(
      @SerializedName("login") val login: String,
      @SerializedName("avatar_url") val avatarUrl: String
  )
}
