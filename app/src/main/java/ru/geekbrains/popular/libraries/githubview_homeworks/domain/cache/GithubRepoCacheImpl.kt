package ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoOwner
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

class GithubRepoCacheImpl(
    private val db: AppDatabase
) : GithubRepoCache {
    override fun getCacheRepo(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return db.repositoryDao.getByUserId(userModel.id)
            .map { list ->
                list.map { repo ->
                    GithubRepoModel(
                        repo.id, repo.name, GithubRepoOwner(repo.userId), repo.forksCount
                    )
                }
            }
    }
}