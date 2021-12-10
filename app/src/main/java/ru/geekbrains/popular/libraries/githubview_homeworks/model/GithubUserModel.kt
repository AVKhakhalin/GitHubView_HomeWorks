package ru.geekbrains.popular.libraries.githubview_homeworks.model

import com.google.gson.annotations.Expose

class GithubUserModel(
    @Expose
    val login: String,

    @Expose
    val avatarUrl: String
)