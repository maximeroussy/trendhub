package com.maximeroussy.trendhub.domain.usecases

import com.maximeroussy.trendhub.RxSchedulerRule
import com.maximeroussy.trendhub.domain.repository.DataRepository
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetGithubRepositoryContentsTest {
  @get:Rule val rxSchedulerRule: TestRule = RxSchedulerRule()
  @get:Rule val mockitoRule: MockitoRule = MockitoJUnit.rule()

  @Mock private lateinit var dataRepository: DataRepository

  private lateinit var sut: GetGithubRepositoryContents

  @Before
  fun setUp() {
    sut = GetGithubRepositoryContents(dataRepository)
  }
}
