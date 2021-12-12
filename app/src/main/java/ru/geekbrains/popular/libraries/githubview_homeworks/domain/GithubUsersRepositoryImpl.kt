package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.db.model.RoomGithubUser
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus

class GithubUsersRepositoryImpl(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val db: AppDatabase
): GithubUsersRepository {

//    override fun getUsers(): Single<List<GithubUserModel>> {
//        return retrofitService.getUsers()
//    }

    override fun getUsers(): Single<List<GithubUserModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomModel ->
                    GithubUserModel(roomModel.id, roomModel.login, roomModel.avatarUrl, roomModel.reposUrl)
                }
            }
        }
    }
}