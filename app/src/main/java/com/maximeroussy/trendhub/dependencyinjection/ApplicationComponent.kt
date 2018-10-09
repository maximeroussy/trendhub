package com.maximeroussy.trendhub.dependencyinjection

import com.maximeroussy.trendhub.presentation.trending.TrendingActivity
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
  fun inject(trendingActivity: TrendingActivity)
}
