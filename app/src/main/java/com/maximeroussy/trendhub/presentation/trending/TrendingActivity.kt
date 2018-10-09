package com.maximeroussy.trendhub.presentation.trending

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.maximeroussy.trendhub.R.layout
import com.maximeroussy.trendhub.dependencyinjection.Injector
import com.maximeroussy.trendhub.presentation.ViewModelFactory
import kotlinx.android.synthetic.main.activity_trending.*
import javax.inject.Inject

class TrendingActivity : AppCompatActivity() {
  @Inject internal lateinit var viewModelFactory: ViewModelFactory<TrendingViewModel>
  private lateinit var viewModel: TrendingViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_trending)
    Injector.getComponent().inject(this)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrendingViewModel::class.java)
    setSupportActionBar(toolbar)
  }
}
