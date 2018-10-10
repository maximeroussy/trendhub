package com.maximeroussy.trendhub.data.mappers

import com.maximeroussy.trendhub.data.api.models.GithubRepositoryResponse
import com.maximeroussy.trendhub.data.api.models.GithubRepositorySearchResponse
import com.maximeroussy.trendhub.domain.models.GithubRepository
import io.wax911.emojify.EmojiUtils
import javax.inject.Inject

class GithubRepositoryMapper @Inject constructor() {
  fun map(githubRepositoryResponse: GithubRepositoryResponse): GithubRepository {
    return GithubRepository(
        githubRepositoryResponse.name,
        githubRepositoryResponse.fullName,
        EmojiUtils.emojify(githubRepositoryResponse.description) ?: "",
        githubRepositoryResponse.language ?: "",
        githubRepositoryResponse.owner.login,
        githubRepositoryResponse.stargazersCount,
        githubRepositoryResponse.forksCount
    )
  }
}
