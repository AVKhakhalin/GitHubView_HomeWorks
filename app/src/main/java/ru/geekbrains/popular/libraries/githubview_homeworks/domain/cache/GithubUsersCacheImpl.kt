package ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

class GithubUsersCacheImpl (
    private val db: AppDatabase
): GithubUsersCache {
    override fun getCacheUsers(): Single<List<GithubUserModel>> {
        return db.userDao.getAll()
            .map { roomModel ->
                roomModel.map {
                    GithubUserModel(it.id, it.login, it.avatarUrl, it.reposUrl)
                }
            }
    }
}