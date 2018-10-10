package com.maximeroussy.trendhub.presentation.trending

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maximeroussy.trendhub.R
import com.maximeroussy.trendhub.R.layout
import com.maximeroussy.trendhub.databinding.ActivityTrendingBinding
import com.maximeroussy.trendhub.dependencyinjection.Injector
import com.maximeroussy.trendhub.presentation.BaseActivity
import com.maximeroussy.trendhub.presentation.ViewModelFactory
import com.maximeroussy.trendhub.presentation.detail.RepositoryDetailActivity
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlinx.android.synthetic.main.activity_trending.recycler_view
import kotlinx.android.synthetic.main.activity_trending.toolbar
import javax.inject.Inject

class TrendingActivity : BaseActivity() {
  @Inject internal lateinit var viewModelFactory: ViewModelFactory<TrendingViewModel>
  private lateinit var viewModel: TrendingViewModel
  private lateinit var adapter: TrendingRepositoryAdapter
  private var isLoading = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = DataBindingUtil.setContentView<ActivityTrendingBinding>(this, layout.activity_trending)
    Injector.getComponent().inject(this)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrendingViewModel::class.java)
    binding.viewModel = viewModel
    setupToolbar()
    setupRecyclerView()
    setupObservers()
    viewModel.fetchAndroidTrendingRepositories()
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
    supportActionBar?.let {
      it.title = getString(R.string.trending)
    }
  }

  private fun setupRecyclerView() {
    val recyclerView = recycler_view
    val layoutManager = LinearLayoutManager(this)
    adapter = TrendingRepositoryAdapter(ArrayList())
    adapter.setOnClickListener { startActivity(RepositoryDetailActivity.newInstance(this, it)) }
    recyclerView.layoutManager = layoutManager
    recyclerView.itemAnimator = ScaleInAnimator()
    recyclerView.adapter = AlphaInAnimationAdapter(adapter)
    recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (isLoading) return
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
        if (pastVisibleItems + visibleItemCount >= totalItemCount) {
          viewModel.fetchAndroidTrendingRepositories()
          isLoading = true
        }
      }
    })
  }

  private fun setupObservers() {
    viewModel.getRepositoryResult.observe(this, Observer {
      adapter.addData(it)
      isLoading = false
    })
    viewModel.getRepositoryFetchError.observe(this, Observer { showErrorDialog(R.string.trending_repo_error) })
  }
}
