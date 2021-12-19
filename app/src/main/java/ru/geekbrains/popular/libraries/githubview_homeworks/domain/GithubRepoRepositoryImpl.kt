package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCacheRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

class GithubRepoRepositoryImpl(
    private val roomGithubRepositoriesCache: GithubRepoCacheRepository
) : GithubRepoRepository {

    override fun getRepos(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return roomGithubRepositoriesCache.getCacheRepo(userModel)
    }
}