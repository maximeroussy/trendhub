package com.maximeroussy.trendhub.domain.repository

import com.maximeroussy.trendhub.domain.models.GithubRepositoryDetail
import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile
import com.maximeroussy.trendhub.domain.models.GithubRepositoryReadme
import io.reactivex.Single

interface DataRepository {
  fun getAndroidTrendingRepositories(): Single<List<GithubRepository>>
  fun getRepositoryDetail(owner: String, repoName: String): Single<GithubRepositoryDetail>
  fun getRepositoryReadme(owner: String, repoName: String): Single<GithubRepositoryReadme>
  fun getRepositoryContents(owner: String, repoName: String): Single<List<GithubRepositoryContentFile>>
}
