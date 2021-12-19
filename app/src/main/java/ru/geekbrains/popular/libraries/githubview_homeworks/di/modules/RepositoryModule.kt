package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubRepoRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCacheRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCacheRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubRepoCacheRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCacheRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun usersRepo(
        usersRepoCache: GithubUsersCacheRepository
    ): GithubUsersRepository {
        return GithubUsersRepositoryImpl(usersRepoCache)
    }

    @Singleton
    @Provides
    fun usersRepoCache(
        networkStatus: NetworkStatus,
        retrofitService: RetrofitService,
        db: AppDatabase
    ): GithubUsersCacheRepository {
        return GithubUsersCacheRepositoryImpl(networkStatus, retrofitService, db)
    }

    @Singleton
    @Provides
    fun reposRepo(
        reposRepoCache: GithubRepoCacheRepository
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(reposRepoCache)
    }

    @Singleton
    @Provides
    fun reposRepoCache(
        networkStatus: NetworkStatus,
        retrofitService: RetrofitService,
        db: AppDatabase
    ): GithubRepoCacheRepository {
        return GithubRepoCacheRepositoryImpl(networkStatus, retrofitService, db)
    }
}