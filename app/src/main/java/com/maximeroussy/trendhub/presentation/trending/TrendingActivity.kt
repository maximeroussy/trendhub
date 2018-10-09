package com.maximeroussy.trendhub.presentation.trending

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximeroussy.trendhub.R.layout
import kotlinx.android.synthetic.main.activity_trending.*

class TrendingActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_trending)
    setSupportActionBar(toolbar)
  }
}
