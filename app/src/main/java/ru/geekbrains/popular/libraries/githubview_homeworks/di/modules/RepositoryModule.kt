package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCache
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCacheImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubRepoRetrofit
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubRepoRetrofitImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun reposRepo(
        networkStatus: NetworkStatus,
        githubRepoRetrofit: GithubRepoRetrofit,
        githubRepoCache: GithubRepoCache
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(networkStatus, githubRepoRetrofit, githubRepoCache)
    }

    @Provides
    @Singleton
    fun reposRetrofit(
        retrofitService: RetrofitService,
        db: AppDatabase
    ): GithubRepoRetrofit {
        return GithubRepoRetrofitImpl(retrofitService, db)
    }

    @Provides
    @Singleton
    fun reposCache(
        db: AppDatabase
    ): GithubRepoCache {
        return GithubRepoCacheImpl(db)
    }

    @Provides
    @Singleton
    fun userChoose(): UserChooseRepository {
        return UserChooseRepositoryImpl()
    }
}