package com.maximeroussy.trendhub.domain.models

data class GithubRepositoryDetail(
    val name: String,
    val fullName: String,
    val description: String,
    val language: String,
    val ownerUsername: String,
    val avatarUrl: String,
    val topics: List<String>,
    val licenseName: String,
    val stars: Int,
    val watchers: Int,
    val forks: Int,
    val openIssuesCount: Int,
    val issuesUrl: String,
    val pullRequestsUrl: String
)
