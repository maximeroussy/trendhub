package com.maximeroussy.trendhub.presentation.trending

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maximeroussy.trendhub.R
import com.maximeroussy.trendhub.R.layout
import com.maximeroussy.trendhub.databinding.ActivityTrendingBinding
import com.maximeroussy.trendhub.dependencyinjection.Injector
import com.maximeroussy.trendhub.presentation.BaseActivity
import com.maximeroussy.trendhub.presentation.ViewModelFactory
import kotlinx.android.synthetic.main.activity_trending.recycler_view
import kotlinx.android.synthetic.main.activity_trending.toolbar
import javax.inject.Inject

class TrendingActivity : BaseActivity() {
  @Inject internal lateinit var viewModelFactory: ViewModelFactory<TrendingViewModel>
  private lateinit var viewModel: TrendingViewModel
  private lateinit var adapter: TrendingRepositoryAdapter

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
    adapter = TrendingRepositoryAdapter(ArrayList())
    adapter.setOnClickListener {  }
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
  }

  private fun setupObservers() {
    viewModel.getRepositoryResult.observe(this, Observer { adapter.addData(it) })
    viewModel.getRepositoryFetchError.observe(this, Observer { showErrorDialog(R.string.trending_repo_error) })
  }
}
