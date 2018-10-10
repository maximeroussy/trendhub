package com.maximeroussy.trendhub.domain.usecases

import com.maximeroussy.trendhub.domain.repository.DataRepository
import com.maximeroussy.trendhub.domain.models.GithubRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

class GetAndroidTrendingRepositories @Inject constructor(private val dataRepository: DataRepository) {
  fun execute(): Single<List<GithubRepository>> {
    return dataRepository.getAndroidTrendingRepositories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
  }
}
