package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.ReposScope
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers.ReposScopeContainer
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCache
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCacheImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubRepoRetrofit
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubRepoRetrofitImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus

@Module
abstract class GithubReposModule {
    companion object {
        @ReposScope
        @Provides
        fun reposRepo(
            networkStatus: NetworkStatus,
            githubRepoRetrofit: GithubRepoRetrofit,
            githubRepoCache: GithubRepoCache
        ): GithubRepoRepository {
            return GithubRepoRepositoryImpl(networkStatus, githubRepoRetrofit, githubRepoCache)
        }

        @ReposScope
        @Provides
        fun reposRetrofit(
            retrofitService: RetrofitService,
            db: AppDatabase
        ): GithubRepoRetrofit {
            return GithubRepoRetrofitImpl(retrofitService, db)
        }

        @ReposScope
        @Provides
        fun reposCache(
            db: AppDatabase
        ): GithubRepoCache {
            return GithubRepoCacheImpl(db)
        }

        @ReposScope
        @Provides
        fun scopeContainer(app: App): ReposScopeContainer = app
    }
}