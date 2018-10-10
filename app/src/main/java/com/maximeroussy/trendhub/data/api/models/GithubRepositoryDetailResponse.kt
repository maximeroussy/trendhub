package com.maximeroussy.trendhub.data.api.models

import com.google.gson.annotations.SerializedName

data class GithubRepositoryDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("description") val description: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("issues_url") val issuesUrl: String,
    @SerializedName("pulls_url") val pullsUrl: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    @SerializedName("topics") val topics: List<String>?,
    @SerializedName("license") val license: License
) {

  data class Owner(
      @SerializedName("login") val login: String,
      @SerializedName("avatar_url") val avatarUrl: String
  )

  data class License(
      @SerializedName("spdx_id") val spdxId: String
  )
}
