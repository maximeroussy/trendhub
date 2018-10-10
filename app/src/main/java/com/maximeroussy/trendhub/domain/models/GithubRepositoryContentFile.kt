package com.maximeroussy.trendhub.domain.models

data class GithubRepositoryContentFile(
    val name: String,
    val size: Int,
    val type: GithubFileType
) {
  enum class GithubFileType {
    FILE, DIRECTORY
  }
}
