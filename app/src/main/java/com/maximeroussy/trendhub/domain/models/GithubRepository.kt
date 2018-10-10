package com.maximeroussy.trendhub.domain.models

import java.io.Serializable

data class GithubRepository(
    val name: String,
    val fullName: String,
    val description: String,
    val language: String,
    val ownerUsername: String,
    val stars: Int,
    val forks: Int
) : Serializable
