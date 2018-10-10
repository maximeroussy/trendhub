package com.maximeroussy.trendhub.data.api

import com.maximeroussy.trendhub.data.api.models.GithubRepositoryContentItemResponse
import com.maximeroussy.trendhub.data.api.models.GithubRepositoryDetailResponse
import com.maximeroussy.trendhub.data.api.models.GithubRepositorySearchResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubEndpoint {
  @GET("/search/repositories?per_page=20&sort=stars&order=desc")
  fun getRepositories(@Query("q") query: String, @Query("page") page: Int):
      Single<Response<GithubRepositorySearchResponse>>

  @Headers("Accept: application/vnd.github.mercy-preview+json")
  @GET("/repos/{owner}/{repo}")
  fun getRepositoryDetail(@Path("owner") owner: String, @Path("repo") repoName: String):
      Single<Response<GithubRepositoryDetailResponse>>

  @Headers("Accept: application/vnd.github.VERSION.html")
  @GET("/repos/{owner}/{repo}/readme")
  fun getRepositoryReadme(@Path("owner") owner: String, @Path("repo") repoName: String):
      Single<Response<String>>

  @GET("repos/{owner}/{repo}/contents")
  fun getRepositoryRootFiles(@Path("owner") owner: String, @Path("repo") repoName: String):
      Single<Response<List<GithubRepositoryContentItemResponse>>>
}
