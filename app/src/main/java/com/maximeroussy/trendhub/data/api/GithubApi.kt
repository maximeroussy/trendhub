package com.maximeroussy.trendhub.data.api

import com.maximeroussy.trendhub.data.api.models.GithubRepositorySearchResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class GithubApi @Inject constructor(private val githubEndpoint: GithubEndpoint) {
  fun getAndroidTrending(date: String): Single<GithubRepositorySearchResponse> {
    val constructedQuery = "topic:android"
    return githubEndpoint.getRepositories(constructedQuery, 0)
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
