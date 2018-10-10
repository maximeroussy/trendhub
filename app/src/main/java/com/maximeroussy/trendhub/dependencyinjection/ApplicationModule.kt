package com.maximeroussy.trendhub.dependencyinjection

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val context: Context) {
  @Provides
  fun context(): Context = context
}
