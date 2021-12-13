package ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.db.model.RoomGithubRepo
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoOwner
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.ApiHolder
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus

class RoomGithubRepositoriesCache(
    private val networkStatus: NetworkStatus
): GithubRepoCacheRepository {
    private val retrofitService: RetrofitService = ApiHolder.retrofitService
    private val db: AppDatabase = AppDatabase.instance

    override fun getCacheRepo(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(userModel.reposUrl)
                .flatMap { repos ->
                    Single.fromCallable {
                        val dbRepos = repos.map {
                            RoomGithubRepo(it.id, it.name, it.owner.id, it.forksCount)
                        }
                        db.repositoryDao.insert(dbRepos)
                        repos
                    }
                }
        } else {
            Single.fromCallable {
                db.repositoryDao.getByUserId(userModel.id)
                    .map { GithubRepoModel(
                        it.id, it.name, GithubRepoOwner(it.userId), it.forksCount) }
            }
        }
    }
}