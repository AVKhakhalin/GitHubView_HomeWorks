package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCache
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubRepoRetrofit
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val githubRepoRetrofit: GithubRepoRetrofit,
    private val githubRepoCache: GithubRepoCache
) : GithubRepoRepository {
    override fun getRepos(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline())
            githubRepoRetrofit.getRetrofitRepo(userModel)
        else
            githubRepoCache.getCacheRepo(userModel)
    }
}