package ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

interface GithubUsersRetrofit {
    fun getRetrofitUsers(): Single<List<GithubUserModel>>
}