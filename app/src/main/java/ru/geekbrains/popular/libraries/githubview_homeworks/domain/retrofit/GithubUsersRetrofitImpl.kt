package ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.db.model.RoomGithubUser
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService

class GithubUsersRetrofitImpl(
    private val retrofitService: RetrofitService,
    private val db: AppDatabase
) : GithubUsersRetrofit {
    override fun getRetrofitUsers(): Single<List<GithubUserModel>> {
        return retrofitService.getUsers()
            .flatMap { users ->
                val roomUsers = users.map { user ->
                    RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
                }
                db.userDao.insert(roomUsers)
                    .toSingle { users }
            }
    }
}