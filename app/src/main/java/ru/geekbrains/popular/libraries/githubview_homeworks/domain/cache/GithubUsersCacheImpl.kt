package ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import javax.inject.Inject

class GithubUsersCacheImpl (
    private val db: AppDatabase
): GithubUsersCache {
    override fun getCacheUsers(): Single<List<GithubUserModel>> {
        return Single.fromCallable {
            db.userDao.getAll().map { roomModel ->
                GithubUserModel(
                    roomModel.id, roomModel.login, roomModel.avatarUrl, roomModel.reposUrl
                )
            }
        }
    }
}