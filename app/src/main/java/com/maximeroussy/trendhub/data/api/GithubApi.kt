package com.maximeroussy.trendhub.data.api

import com.maximeroussy.trendhub.data.api.models.GithubRepositoryContentItemResponse
import com.maximeroussy.trendhub.data.api.models.GithubRepositoryDetailResponse
import com.maximeroussy.trendhub.data.api.models.GithubRepositorySearchResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GithubApi @Inject constructor(
    private val githubEndpoint: GithubEndpoint,
    private val githubHeaderParser: GithubHeaderParser
) {

  private var nextPageIndex = 0
  private val constructedQuery = "topic:android"

  fun getAndroidTrending(): Single<GithubRepositorySearchResponse> {
    return if (nextPageIndex == -1) {
      Single.just(GithubRepositorySearchResponse(0, ArrayList()))
    } else {
      githubEndpoint.getRepositories(constructedQuery, nextPageIndex)
          .doOnSuccess { nextPageIndex = githubHeaderParser.getNextPage(it.headers()) }
          .flatMap { mapRetrofitResponse(it) }
    }
  }

  fun getRepositoryDetail(owner: String, repoName: String): Single<GithubRepositoryDetailResponse> {
    return githubEndpoint.getRepositoryDetail(owner, repoName)
        .flatMap { mapRetrofitResponse(it) }
  }

  fun getRepositoryReadme(owner: String, repoName: String): Single<String> {
    return githubEndpoint.getRepositoryReadme(owner, repoName)
        .flatMap { mapRetrofitResponse(it) }
  }

  fun getRepositoryContents(owner: String, repoName: String): Single<List<GithubRepositoryContentItemResponse>> {
    return githubEndpoint.getRepositoryRootFiles(owner, repoName)
        .flatMap { mapRetrofitResponse(it) }
  }

  private fun <T> mapRetrofitResponse(response: Response<T>): Single<T> {
    return if (response.isSuccessful) {
      Single.just(response.body()!!)
    } else {
      Single.error(Throwable(response.errorBody().toString()))
    }
  }
}
