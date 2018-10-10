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

class GetAndroidTrendingRepositoriesTest {
  @get:Rule val rxSchedulerRule: TestRule = RxSchedulerRule()
  @get:Rule val mockitoRule: MockitoRule = MockitoJUnit.rule()

  @Mock private lateinit var dataRepository: DataRepository

  private lateinit var sut: GetAndroidTrendingRepositories

  @Before
  fun setUp() {
    sut = GetAndroidTrendingRepositories(dataRepository)
  }

  @Test
  fun `execute calls dataRepository`() {
    given { dataRepository.getAndroidTrendingRepositories() } willReturn { Single.just(mock()) }

    sut.execute().test()

    verify(dataRepository).getAndroidTrendingRepositories()
  }
}
