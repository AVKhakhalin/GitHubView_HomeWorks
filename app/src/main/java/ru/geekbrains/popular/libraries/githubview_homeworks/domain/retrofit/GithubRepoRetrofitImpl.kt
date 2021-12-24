package ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.db.model.RoomGithubRepo
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService

class GithubRepoRetrofitImpl(
    private val retrofitService: RetrofitService,
    private val db: AppDatabase
) : GithubRepoRetrofit {
    override fun getRetrofitRepo(userModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return retrofitService.getRepos(userModel.reposUrl)
            .flatMap { repos ->
                val dbRepos = repos.map {
                    RoomGithubRepo(it.id, it.name, it.owner.id, it.forksCount)
                }
                db.repositoryDao.insert(dbRepos)
                    .toSingle { repos }
            }
    }
}