package com.maximeroussy.trendhub.data.mappers

import com.maximeroussy.trendhub.data.api.models.GithubRepositoryDetailResponse
import com.maximeroussy.trendhub.domain.models.GithubRepositoryDetail
import javax.inject.Inject

class GithubRepositoryDetailMapper @Inject constructor() {
  fun map(githubRepositoryDetailResponse: GithubRepositoryDetailResponse): GithubRepositoryDetail {
    return GithubRepositoryDetail(
        githubRepositoryDetailResponse.name,
        githubRepositoryDetailResponse.fullName,
        githubRepositoryDetailResponse.description ?: "",
        githubRepositoryDetailResponse.language ?: "",
        githubRepositoryDetailResponse.owner.login,
        githubRepositoryDetailResponse.owner.avatarUrl,
        githubRepositoryDetailResponse.topics ?: ArrayList(),
        githubRepositoryDetailResponse.license.spdxId,
        githubRepositoryDetailResponse.stargazersCount,
        githubRepositoryDetailResponse.watchersCount,
        githubRepositoryDetailResponse.forksCount,
        githubRepositoryDetailResponse.openIssuesCount,
        githubRepositoryDetailResponse.issuesUrl,
        githubRepositoryDetailResponse.pullsUrl
    )
  }
}
