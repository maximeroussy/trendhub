package com.maximeroussy.trendhub.dependencyinjection

import com.maximeroussy.trendhub.data.repository.DataRepositoryImpl
import com.maximeroussy.trendhub.domain.repository.DataRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
  @Provides
  fun dataRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository {
    return dataRepositoryImpl
  }
}
