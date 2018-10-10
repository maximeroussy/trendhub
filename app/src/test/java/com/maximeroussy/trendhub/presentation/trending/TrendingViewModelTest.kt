package com.maximeroussy.trendhub.presentation.trending

import com.maximeroussy.trendhub.RxSchedulerRule
import com.maximeroussy.trendhub.domain.usecases.GetAndroidTrendingRepositories
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class TrendingViewModelTest {
  @get:Rule val rxSchedulerRule: TestRule = RxSchedulerRule()
  @get:Rule val mockitoRule: MockitoRule = MockitoJUnit.rule()

  @Mock private lateinit var getAndroidTrendingRepositories: GetAndroidTrendingRepositories

  private lateinit var sut: TrendingViewModel

  @Before
  fun setUp() {
    sut = TrendingViewModel(getAndroidTrendingRepositories)
  }
}
