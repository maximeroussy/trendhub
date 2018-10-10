package com.maximeroussy.trendhub.presentation.trending

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.domain.usecases.GetAndroidTrendingRepositories
import com.maximeroussy.trendhub.presentation.SingleLiveEvent
import com.maximeroussy.trendhub.util.addTo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TrendingViewModel @Inject constructor(
    private val getAndroidTrendingRepositories: GetAndroidTrendingRepositories
) : ViewModel() {

  var isEmptyList = ObservableField<Boolean>(false)

  private val disposables = CompositeDisposable()
  private val repositoryResults = MutableLiveData<List<GithubRepository>>()
  private val repositoryFetchError = SingleLiveEvent<Any>()

  val getRepositoryResult: LiveData<List<GithubRepository>>
    get() = repositoryResults

  val getRepositoryFetchError: LiveData<Any>
    get() = repositoryFetchError

  init {
    fetchAndroidTrendingRepositories()
  }

  fun fetchAndroidTrendingRepositories() {
    getAndroidTrendingRepositories.execute()
        .subscribe({ result ->
          repositoryResults.value = result
          isEmptyList.set(result.isEmpty())
        }, { repositoryFetchError.call() })
        .addTo(disposables)
  }

  override fun onCleared() {
    disposables.clear()
    super.onCleared()
  }
}
