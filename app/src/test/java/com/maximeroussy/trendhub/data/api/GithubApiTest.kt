package com.maximeroussy.trendhub.data.api

import com.maximeroussy.trendhub.data.api.models.GithubRepositorySearchResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.willReturn
import io.reactivex.Single
import okhttp3.Headers
import okhttp3.ResponseBody
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Response

class GithubApiTest {
  @get:Rule val mockitoRule: MockitoRule = MockitoJUnit.rule()

  @Mock private lateinit var githubEndpoint: GithubEndpoint
  @Mock private lateinit var githubHeaderParser: GithubHeaderParser

  private lateinit var sut: GithubApi

  @Before
  fun setUp() {
    sut = GithubApi(githubEndpoint, githubHeaderParser)
  }

  @Test
  fun `getAndroidTrending calls github endpoint`() {
    given { githubEndpoint.getRepositories(any(), any()) } willReturn { Single.just(mock()) }

    sut.getAndroidTrending().test()

    verify(githubEndpoint).getRepositories(eq(GithubApi.QUERY), eq(0))
  }

  @Test
  fun `getAndroidTrending calls GithubHeaderParser on Success`() {
    val githubRepositorySearchResponse: GithubRepositorySearchResponse = mock()
    val headers = Headers.Builder().build()
    val response = Response.success(githubRepositorySearchResponse, headers)
    given { githubEndpoint.getRepositories(any(), any()) } willReturn { Single.just(response) }
    given { githubHeaderParser.getNextPage(any()) } willReturn { 1 }

    sut.getAndroidTrending().test()

    verify(githubHeaderParser).getNextPage(eq(headers))
  }

  @Test
  fun `getAndroidTrending calls github endpoint with parsed page on second round`() {
    val nextPage = 1
    val githubRepositorySearchResponse: GithubRepositorySearchResponse = mock()
    val headers = Headers.Builder().build()
    val response = Response.success(githubRepositorySearchResponse, headers)
    given { githubEndpoint.getRepositories(any(), any()) } willReturn { Single.just(response) }
    given { githubHeaderParser.getNextPage(any()) } willReturn { nextPage }

    sut.getAndroidTrending().test()
    sut.getAndroidTrending().test()

    verify(githubEndpoint).getRepositories(eq(GithubApi.QUERY), eq(nextPage))
  }

  @Test
  fun `getAndroidTrending returns error through stream on fail`() {
    val response = Response.error<GithubRepositorySearchResponse>(403, mock())
    given { githubEndpoint.getRepositories(any(), any()) } willReturn { Single.just(response) }
    given { githubHeaderParser.getNextPage(any()) } willReturn { 1 }

    val testObserver = sut.getAndroidTrending().test()

    assertThat(testObserver.errorCount(), `is`(1))
  }

  @Test
  fun `getRepositoryDetail calls github endpoint`() {
    val owner = "owner"
    val repo = "repo"
    given { githubEndpoint.getRepositoryDetail(any(), any()) } willReturn { Single.just(mock()) }

    sut.getRepositoryDetail(owner, repo).test()

    verify(githubEndpoint).getRepositoryDetail(eq(owner), eq(repo))
  }
}
