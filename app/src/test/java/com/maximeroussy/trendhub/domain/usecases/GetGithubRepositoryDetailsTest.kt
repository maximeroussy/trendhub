package com.maximeroussy.trendhub.domain.usecases

import com.maximeroussy.trendhub.RxSchedulerRule
import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.domain.repository.DataRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.willReturn
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetGithubRepositoryDetailsTest {
  @get:Rule val rxSchedulerRule: TestRule = RxSchedulerRule()
  @get:Rule val mockitoRule: MockitoRule = MockitoJUnit.rule()

  @Mock private lateinit var dataRepository: DataRepository

  private lateinit var sut: GetGithubRepositoryDetails

  @Before
  fun setUp() {
    sut = GetGithubRepositoryDetails(dataRepository)
  }

  @Test
  fun `execute calls dataRepository`() {
    val owner = "owner"
    val repo = "repo"
    val githubRepository = GithubRepository(repo, "", "", "", owner, 0, 0)
    given { dataRepository.getRepositoryDetail(any(), any()) } willReturn { Single.just(mock()) }

    sut.execute(githubRepository).test()

    verify(dataRepository).getRepositoryDetail(eq(owner), eq(repo))
  }
}