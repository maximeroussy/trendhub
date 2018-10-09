package com.maximeroussy.trendhub.dependencyinjection

import com.maximeroussy.trendhub.TrendhubApplication

class Injector private constructor() {
  companion object {
    fun getComponent() : ApplicationComponent = TrendhubApplication.get().component
  }
}
