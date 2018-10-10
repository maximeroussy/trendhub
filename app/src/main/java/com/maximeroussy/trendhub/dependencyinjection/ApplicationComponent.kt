package com.maximeroussy.trendhub.dependencyinjection

import com.maximeroussy.trendhub.presentation.detail.RepositoryDetailActivity
import com.maximeroussy.trendhub.presentation.trending.TrendingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ApiModule::class, RepositoryModule::class])
interface ApplicationComponent {
  fun inject(trendingActivity: TrendingActivity)
  fun inject(repositoryDetailActivity: RepositoryDetailActivity)
}
