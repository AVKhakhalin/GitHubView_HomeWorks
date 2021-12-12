package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService

class GithubUsersRepositoryImpl(
    private val retrofitService: RetrofitService,
): GithubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> {
        return retrofitService.getUsers()
    }
}