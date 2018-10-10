package com.maximeroussy.trendhub.data.repository

import com.maximeroussy.trendhub.data.api.GithubApi
import com.maximeroussy.trendhub.data.api.models.GithubRepositoryDetailResponse
import com.maximeroussy.trendhub.data.api.models.GithubRepositoryResponse
import com.maximeroussy.trendhub.data.api.models.GithubRepositorySearchResponse
import com.maximeroussy.trendhub.data.mappers.GithubRepositoryContentFileMapper
import com.maximeroussy.trendhub.data.mappers.GithubRepositoryDetailMapper
import com.maximeroussy.trendhub.data.mappers.GithubRepositoryMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.willReturn
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class DataRepositoryTest {
  @get:Rule val mockitoRule: MockitoRule = MockitoJUnit.rule()

  @Mock private lateinit var githubApi: GithubApi
  @Mock private lateinit var githubRepositoryMapper: GithubRepositoryMapper
  @Mock private lateinit var githubRepositoryDetailMapper: GithubRepositoryDetailMapper
  @Mock private lateinit var githubRepositoryContentFileMapper: GithubRepositoryContentFileMapper

  private lateinit var sut: DataRepositoryImpl

  @Before
  fun setUp() {
    sut = DataRepositoryImpl(githubApi, githubRepositoryMapper, githubRepositoryDetailMapper,
        githubRepositoryContentFileMapper)
  }

  @Test
  fun `getAndroidTrendingRepositories calls githubApi`() {
    given { githubApi.getAndroidTrending() } willReturn { Single.just(mock()) }
    given { githubRepositoryMapper.map(any()) } willReturn { mock() }

    sut.getAndroidTrendingRepositories().test()

    verify(githubApi).getAndroidTrending()
  }

  @Test
  fun `getAndroidTrendingRepositories calls githubRepositoryMapper`() {
    val githubRepositoryResponseList = ArrayList<GithubRepositoryResponse>(3)
    for (i in 0..2) {
      githubRepositoryResponseList.add(i, mock())
    }
    val response = GithubRepositorySearchResponse(3, githubRepositoryResponseList)
    given { githubApi.getAndroidTrending() } willReturn { Single.just(response) }
    given { githubRepositoryMapper.map(any()) } willReturn { mock() }

    sut.getAndroidTrendingRepositories().test()

    verify(githubRepositoryMapper, times(3)).map(any())
  }

  @Test
  fun `getRepositoryDetail calls githubApi`() {
    val owner = "owner"
    val repo = "repo"
    given { githubApi.getRepositoryDetail(any(), any()) } willReturn { Single.just(mock()) }
    given { githubRepositoryDetailMapper.map(any()) } willReturn { mock() }

    sut.getRepositoryDetail(owner, repo).test()

    verify(githubApi).getRepositoryDetail(eq(owner), eq(repo))
  }

  @Test
  fun `getRepositoryDetail calls githubRepositoryDetailMapper`() {
    val owner = "owner"
    val repo = "repo"
    val response: GithubRepositoryDetailResponse = mock()
    given { githubApi.getRepositoryDetail(any(), any()) } willReturn { Single.just(response) }
    given { githubRepositoryDetailMapper.map(any()) } willReturn { mock() }

    sut.getRepositoryDetail(owner, repo).test()

    verify(githubRepositoryDetailMapper).map(eq(response))
  }
}
