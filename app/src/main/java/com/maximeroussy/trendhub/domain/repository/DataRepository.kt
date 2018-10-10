package com.maximeroussy.trendhub.domain.repository

import com.maximeroussy.trendhub.domain.models.GithubRepository
import io.reactivex.Single

interface DataRepository {
  fun getAndroidTrendingRepositories(): Single<List<GithubRepository>>
}
