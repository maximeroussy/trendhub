package com.maximeroussy.trendhub.presentation.detail

import com.maximeroussy.trendhub.RxSchedulerRule
import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.domain.usecases.GetGithubRepositoryContents
import com.maximeroussy.trendhub.domain.usecases.GetGithubRepositoryDetails
import com.maximeroussy.trendhub.domain.usecases.GetGithubRepositoryReadme
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class RepositoryDetailViewModelTest {
  @get:Rule val rxSchedulerRule: TestRule = RxSchedulerRule()
  @get:Rule val mockitoRule: MockitoRule = MockitoJUnit.rule()

  @Mock private lateinit var getGithubRepositoryDetails: GetGithubRepositoryDetails
  @Mock private lateinit var getGithubRepositoryContents: GetGithubRepositoryContents
  @Mock private lateinit var getGithubRepositoryReadme: GetGithubRepositoryReadme

  private lateinit var sut: RepositoryDetailViewModel

  @Before
  fun setUp() {
    sut = RepositoryDetailViewModel(getGithubRepositoryDetails, getGithubRepositoryContents, getGithubRepositoryReadme)
  }

  @Test
  fun `fetchData(GithubRepository) will call GetGithubRepositoryDetails usecase execute method`() {
    val githubRepository: GithubRepository = mock()
    given(getGithubRepositoryDetails.execute(any())).willReturn(Single.just(mock()))
    given(getGithubRepositoryContents.execute(any())).willReturn(Single.just(ArrayList()))
    given(getGithubRepositoryReadme.execute(any())).willReturn(Single.just(mock()))

    sut.fetchData(githubRepository)

    verify(getGithubRepositoryDetails).execute(eq(githubRepository))
  }

  @Test
  fun `fetchData(GithubRepository) will call GetGithubRepositoryContents usecase execute method`() {
    val githubRepository: GithubRepository = mock()
    given(getGithubRepositoryDetails.execute(any())).willReturn(Single.just(mock()))
    given(getGithubRepositoryContents.execute(any())).willReturn(Single.just(ArrayList()))
    given(getGithubRepositoryReadme.execute(any())).willReturn(Single.just(mock()))

    sut.fetchData(githubRepository)

    verify(getGithubRepositoryContents).execute(eq(githubRepository))
  }

  @Test
  fun `fetchData(GithubRepository) will call GetGithubRepositoryReadme usecase execute method`() {
    val githubRepository: GithubRepository = mock()
    given(getGithubRepositoryDetails.execute(any())).willReturn(Single.just(mock()))
    given(getGithubRepositoryContents.execute(any())).willReturn(Single.just(ArrayList()))
    given(getGithubRepositoryReadme.execute(any())).willReturn(Single.just(mock()))

    sut.fetchData(githubRepository)

    verify(getGithubRepositoryReadme).execute(eq(githubRepository))
  }
}
