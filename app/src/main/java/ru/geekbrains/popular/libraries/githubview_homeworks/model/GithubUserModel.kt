package ru.geekbrains.popular.libraries.githubview_homeworks.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class GithubUserModel(

    @Expose
    val login: String,
    @Expose
    val avatarUrl: String,
    @Expose
    val reposUrl: String
): Serializable