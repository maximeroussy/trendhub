package com.maximeroussy.trendhub.presentation.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import com.maximeroussy.trendhub.R
import com.maximeroussy.trendhub.R.layout
import com.maximeroussy.trendhub.databinding.ActivityRepositoryDetailBinding
import com.maximeroussy.trendhub.dependencyinjection.Injector
import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile.GithubFileType.DIRECTORY
import com.maximeroussy.trendhub.domain.models.GithubRepositoryContentFile.GithubFileType.FILE
import com.maximeroussy.trendhub.domain.models.GithubRepositoryReadme
import com.maximeroussy.trendhub.presentation.BaseActivity
import com.maximeroussy.trendhub.presentation.ViewModelFactory
import kotlinx.android.synthetic.main.activity_repository_detail.toolbar
import javax.inject.Inject

class RepositoryDetailActivity : BaseActivity() {
  @Inject internal lateinit var viewModelFactory: ViewModelFactory<RepositoryDetailViewModel>
  private lateinit var binding: ActivityRepositoryDetailBinding
  private lateinit var viewModel: RepositoryDetailViewModel
  private lateinit var webView: WebView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, layout.activity_repository_detail)
    Injector.getComponent().inject(this)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepositoryDetailViewModel::class.java)
    binding.viewModel = viewModel
    setupToolbar()
    setupObservers()
    viewModel.fetchData(getGithubRepository())
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
    supportActionBar?.let {
      it.title = ""
      it.setDisplayHomeAsUpEnabled(true)
      it.setHomeAsUpIndicator(R.drawable.ic_close)
    }
  }

  private fun setupObservers() {
    viewModel.getRepositoryDetails.observe(this, Observer { githubRepositoryDetail ->
      binding.repository = githubRepositoryDetail
      val chipGroup = binding.chipGroup
      githubRepositoryDetail.topics.forEach {
        val newChip = Chip(this@RepositoryDetailActivity)
        newChip.text = it
        chipGroup.addView(newChip)
      }
    })
    viewModel.getRepositoryReadme.observe(this, Observer { setupReadmeView(it) })
    viewModel.getRepositoryContents.observe(this, Observer { setupFileTable(it) })
    viewModel.getRepositoryLoadingError.observe(this, Observer { showErrorDialog(R.string.repository_loading_error) })
  }

  private fun setupFileTable(filesList: List<GithubRepositoryContentFile>) {
    val fileTable = binding.fileTable
    val sortedFilesList = sortFilesList(filesList)
    sortedFilesList.forEach { file ->
      val tableRow = TableRow(this@RepositoryDetailActivity)
      tableRow.addView(createTableIconView(file))
      tableRow.addView(createTableNameTextView(file))
      tableRow.addView(createTableSizeTextView(file))
      fileTable.addView(tableRow)
    }
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun setupReadmeView(githubRepositoryReadme: GithubRepositoryReadme) {
    webView = binding.readmeWebview
    webView.webViewClient = WebViewClient()
    webView.settings.javaScriptEnabled = true
    webView.settings.domStorageEnabled = true
    webView.loadData(githubRepositoryReadme.htmlContent, "text/html; charset=utf-8", "UTF-8")
  }

  private fun sortFilesList(filesList: List<GithubRepositoryContentFile>): List<GithubRepositoryContentFile> {
    return filesList.sortedWith(compareByDescending<GithubRepositoryContentFile> { it.type }
            .thenByDescending { it.name.startsWith(".") }
            .thenBy { it.name })
  }

  private fun createTableIconView(file: GithubRepositoryContentFile): View {
    val icon = ImageView(this@RepositoryDetailActivity)
    when(file.type) {
      FILE -> icon.setImageResource(R.drawable.ic_document)
      DIRECTORY -> icon.setImageResource(R.drawable.ic_folder)
    }
    icon.setPadding(0, 10, 20, 10)
    return icon
  }

  private fun createTableNameTextView(file: GithubRepositoryContentFile): View {
    val name = TextView(this@RepositoryDetailActivity)
    name.text = file.name
    name.setTextColor(resources.getColor(R.color.file_name_blue))
    name.setPadding(0, 10, 0, 10)
    return name
  }

  private fun createTableSizeTextView(file: GithubRepositoryContentFile): View {
    val size = TextView(this@RepositoryDetailActivity)
    size.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
    size.setPadding(0, 10, 0, 10)
    val fileSize = file.size
    if (fileSize > 0) {
      size.text = String.format(getString(R.string.file_size_format), fileSize.toDouble()/1000.0)
    }
    return size
  }

  private fun getGithubRepository(): GithubRepository {
    return intent.getSerializableExtra(GITHUB_REPOSITORY) as GithubRepository
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId) {
      android.R.id.home -> finish()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onBackPressed() {
    if (webView.canGoBack()) {
      webView.goBack()
    } else {
      super.onBackPressed()
    }
  }

  companion object {
    private const val GITHUB_REPOSITORY = "github_repository"
    fun newInstance(context: Context, githubRepository: GithubRepository): Intent {
      val intent = Intent(context, RepositoryDetailActivity::class.java)
      intent.putExtra(GITHUB_REPOSITORY, githubRepository)
      return intent
    }
  }
}
