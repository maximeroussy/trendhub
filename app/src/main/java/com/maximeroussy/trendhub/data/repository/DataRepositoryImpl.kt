package com.maximeroussy.trendhub.data.repository

import com.maximeroussy.trendhub.data.api.GithubApi
import com.maximeroussy.trendhub.data.mappers.GithubRepositoryContentFileMapper
import com.maximeroussy.trendhub.data.mappers.GithubRepositoryDetailMapper
import com.maximeroussy.trendhub.data.mappers.GithubRepositoryMapper
import com.maximeroussy.trendhub.domain.models.GithubRepositoryDetail
import com.maximeroussy.trendhub.domain.repository.DataRepository
import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile
import com.maximeroussy.trendhub.domain.models.GithubRepositoryReadme
import io.reactivex.Single
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val githubRepositoryMapper: GithubRepositoryMapper,
    private val githubRepositoryDetailMapper: GithubRepositoryDetailMapper,
    private val githubRepositoryContentFileMapper: GithubRepositoryContentFileMapper
) : DataRepository {

  override fun getAndroidTrendingRepositories(): Single<List<GithubRepository>> {
    return githubApi.getAndroidTrending()
        .map { response -> response.items.map { githubRepositoryMapper.map(it) } }
  }

  override fun getRepositoryDetail(owner: String, repoName: String): Single<GithubRepositoryDetail> {
    return githubApi.getRepositoryDetail(owner, repoName)
        .map { githubRepositoryDetailMapper.map(it) }
  }

  override fun getRepositoryReadme(owner: String, repoName: String): Single<GithubRepositoryReadme> {
    return githubApi.getRepositoryReadme(owner, repoName)
        .map { GithubRepositoryReadme(it) }
  }

  override fun getRepositoryContents(owner: String, repoName: String): Single<List<GithubRepositoryContentFile>> {
    return githubApi.getRepositoryContents(owner, repoName)
        .map { response -> response.map { githubRepositoryContentFileMapper.map(it) } }
  }
}
