package ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

interface GithubUsersCacheRepository {
    fun getCacheUsers(): Single<List<GithubUserModel>>
}