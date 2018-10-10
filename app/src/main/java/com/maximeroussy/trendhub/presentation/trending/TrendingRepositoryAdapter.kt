package com.maximeroussy.trendhub.presentation.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.maximeroussy.trendhub.R
import com.maximeroussy.trendhub.domain.models.GithubRepository
import com.maximeroussy.trendhub.databinding.ViewRepositoryItemBinding
import com.maximeroussy.trendhub.presentation.trending.TrendingRepositoryAdapter.RepositoryViewHolder

class TrendingRepositoryAdapter(private val items: MutableList<GithubRepository>) : RecyclerView.Adapter<RepositoryViewHolder>() {
  private lateinit var clickListener: (GithubRepository) -> Unit

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = DataBindingUtil.inflate<ViewRepositoryItemBinding>(layoutInflater, R.layout.view_repository_item, parent, false)
    return RepositoryViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return items.size
  }

  override fun onBindViewHolder(viewHolder: RepositoryViewHolder, position: Int) {
    val item = items[position]
    viewHolder.bind(item, clickListener)
  }

  fun addData(newData: List<GithubRepository>) {
    newData.forEach {
      this.items.add(it)
      notifyItemInserted(items.indexOf(it))
    }
  }

  fun setOnClickListener(clickListener: (GithubRepository) -> Unit) {
    this.clickListener = clickListener
  }

  class RepositoryViewHolder(private val binding: ViewRepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: GithubRepository, clickListener: (GithubRepository) -> Unit) {
      binding.repository = item
      binding.repositoryCard.setOnClickListener { clickListener(item) }
    }
  }
}
