package ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

interface GithubRepoRetrofit {
    fun getRetrofitRepo(userModel: GithubUserModel): Single<List<GithubRepoModel>>
}