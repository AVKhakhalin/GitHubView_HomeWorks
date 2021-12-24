package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCache
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubUsersRetrofit
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus

class GithubUsersRepositoryImpl(
    private val networkStatus: NetworkStatus,
    private val githubUsersRetrofit: GithubUsersRetrofit,
    private val githubUsersCache: GithubUsersCache
) : GithubUsersRepository {
    override fun getUsers(): Single<List<GithubUserModel>> {
        return if (networkStatus.isOnline())
            githubUsersRetrofit.getRetrofitUsers()
        else
            githubUsersCache.getCacheUsers()
    }
}