package com.maximeroussy.trendhub.domain.usecases

import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.domain.models.GithubRepositoryDetail
import com.maximeroussy.trendhub.domain.repository.DataRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetGithubRepositoryDetails @Inject constructor(private val dataRepository: DataRepository) {
  fun execute(githubRepository: GithubRepository): Single<GithubRepositoryDetail> {
    return dataRepository.getRepositoryDetail(githubRepository.ownerUsername, githubRepository.name)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
  }
}
