package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCacheRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

class GithubUsersRepositoryImpl(
    private val RoomGithubUsersCache: GithubUsersCacheRepository
) : GithubUsersRepository {
    override fun getUsers(): Single<List<GithubUserModel>> {
        return RoomGithubUsersCache.getCacheUsers()
    }
}