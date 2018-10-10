package com.maximeroussy.trendhub.data.api

import com.maximeroussy.trendhub.data.api.models.GithubRepositorySearchResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubEndpoint {
  @GET("/search/repositories?per_page=20&sort=stars&order=desc")
  fun getRepositories(@Query("q") query: String, @Query("page") page: Int):
      Single<Response<GithubRepositorySearchResponse>>
}
