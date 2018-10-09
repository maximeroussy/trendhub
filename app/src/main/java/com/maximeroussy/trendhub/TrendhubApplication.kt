package com.maximeroussy.trendhub

import android.app.Application
import com.maximeroussy.trendhub.dependencyinjection.ApplicationComponent
import com.maximeroussy.trendhub.dependencyinjection.ApplicationModule
import com.maximeroussy.trendhub.dependencyinjection.DaggerApplicationComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class TrendhubApplication : Application() {
  lateinit var component: ApplicationComponent

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
    INSTANCE = this
    component = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
  }

  companion object {
    private var INSTANCE: TrendhubApplication? = null
    @JvmStatic
    fun get(): TrendhubApplication = INSTANCE!!
  }
}
