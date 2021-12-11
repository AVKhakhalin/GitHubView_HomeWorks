package ru.geekbrains.popular.libraries.githubview_homeworks.model

import com.google.gson.annotations.Expose

data class GithubRepoModel(
    @Expose
    val id: String,
    @Expose
    val name: String,
    @Expose
    val owner: GithubRepoOwner,
    @Expose
    val forksCount: Int
)

data class GithubRepoOwner(
    @Expose
    val id: String
)
