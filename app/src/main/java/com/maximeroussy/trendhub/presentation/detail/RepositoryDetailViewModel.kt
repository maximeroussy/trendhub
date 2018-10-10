package com.maximeroussy.trendhub.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile
import com.maximeroussy.trendhub.domain.models.GithubRepositoryDetail
import com.maximeroussy.trendhub.domain.models.GithubRepositoryReadme
import com.maximeroussy.trendhub.domain.usecases.GetGithubRepositoryContents
import com.maximeroussy.trendhub.domain.usecases.GetGithubRepositoryDetails
import com.maximeroussy.trendhub.domain.usecases.GetGithubRepositoryReadme
import com.maximeroussy.trendhub.presentation.SingleLiveEvent
import com.maximeroussy.trendhub.util.addTo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RepositoryDetailViewModel @Inject constructor(
    private val getGithubRepositoryDetails: GetGithubRepositoryDetails,
    private val getGithubRepositoryReadme: GetGithubRepositoryReadme,
    private val getGithubRepositoryContents: GetGithubRepositoryContents
) : ViewModel() {

  private val disposables = CompositeDisposable()
  private val repositoryDetails = MutableLiveData<GithubRepositoryDetail>()
  private val repositoryReadme = MutableLiveData<GithubRepositoryReadme>()
  private val repositoryContents = MutableLiveData<List<GithubRepositoryContentFile>>()
  private val repositoryLoadingError = SingleLiveEvent<Any>()

  val getRepositoryDetails: LiveData<GithubRepositoryDetail>
    get() = repositoryDetails

  val getRepositoryReadme: LiveData<GithubRepositoryReadme>
    get() = repositoryReadme

  val getRepositoryContents: LiveData<List<GithubRepositoryContentFile>>
    get() = repositoryContents

  val getRepositoryLoadingError: LiveData<Any>
    get() = repositoryLoadingError

  fun fetchData(githubRepository: GithubRepository) {
    loadRepositoryDetails(githubRepository)
    loadRepositoryReadme(githubRepository)
    loadRepositoryContents(githubRepository)
  }

  private fun loadRepositoryDetails(githubRepository: GithubRepository) {
    getGithubRepositoryDetails.execute(githubRepository)
        .subscribe({ result -> repositoryDetails.value = result }, { repositoryLoadingError.call() })
        .addTo(disposables)
  }

  private fun loadRepositoryReadme(githubRepository: GithubRepository) {
    getGithubRepositoryReadme.execute(githubRepository)
        .subscribe({ result -> repositoryReadme.value = result }, { repositoryLoadingError.call() })
        .addTo(disposables)
  }

  private fun loadRepositoryContents(githubRepository: GithubRepository) {
    getGithubRepositoryContents.execute(githubRepository)
        .subscribe({ result -> repositoryContents.value = result }, { repositoryLoadingError.call() })
        .addTo(disposables)
  }

  override fun onCleared() {
    disposables.clear()
    super.onCleared()
  }
}
