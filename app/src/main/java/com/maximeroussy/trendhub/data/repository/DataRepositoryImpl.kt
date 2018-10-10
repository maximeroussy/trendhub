package com.maximeroussy.trendhub.data.repository

import com.maximeroussy.trendhub.data.api.GithubApi
import com.maximeroussy.trendhub.data.mappers.GithubRepositoryMapper
import com.maximeroussy.trendhub.domain.repository.DataRepository
import com.maximeroussy.trendhub.domain.models.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val githubRepositoryMapper: GithubRepositoryMapper
) : DataRepository {

  override fun getAndroidTrendingRepositories(date: String): Single<List<GithubRepository>> {
    return githubApi.getAndroidTrending(date)
        .map { githubRepositoryMapper.map(it) }
  }
}
