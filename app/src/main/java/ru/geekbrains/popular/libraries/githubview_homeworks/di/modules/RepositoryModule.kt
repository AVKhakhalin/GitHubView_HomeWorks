package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCache
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCacheImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCache
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCacheImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubRepoRetrofit
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubRepoRetrofitImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubUsersRetrofit
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubUsersRetrofitImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun usersRepo(
        networkStatus: NetworkStatus,
        githubUsersRetrofit: GithubUsersRetrofit,
        githubUsersCache: GithubUsersCache
    ): GithubUsersRepository {
        return GithubUsersRepositoryImpl(networkStatus, githubUsersRetrofit, githubUsersCache)
    }

    @Singleton
    @Provides
    fun usersRetrofit(
        retrofitService: RetrofitService,
        db: AppDatabase
    ): GithubUsersRetrofit {
        return GithubUsersRetrofitImpl(retrofitService, db)
    }

    @Singleton
    @Provides
    fun usersCache(
        db: AppDatabase
    ): GithubUsersCache {
        return GithubUsersCacheImpl(db)
    }

    @Singleton
    @Provides
    fun reposRepo(
        networkStatus: NetworkStatus,
        githubRepoRetrofit: GithubRepoRetrofit,
        githubRepoCache: GithubRepoCache
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(networkStatus, githubRepoRetrofit, githubRepoCache)
    }

    @Singleton
    @Provides
    fun reposRetrofit(
        retrofitService: RetrofitService,
        db: AppDatabase
    ): GithubRepoRetrofit {
        return GithubRepoRetrofitImpl(retrofitService, db)
    }

    @Singleton
    @Provides
    fun reposCache(
        db: AppDatabase
    ): GithubRepoCache {
        return GithubRepoCacheImpl(db)
    }
}