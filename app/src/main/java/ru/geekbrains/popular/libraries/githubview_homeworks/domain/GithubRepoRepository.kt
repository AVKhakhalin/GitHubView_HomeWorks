package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

interface GithubRepoRepository {
    fun getRepos(reposUrl: GithubUserModel): Single<List<GithubRepoModel>>
}