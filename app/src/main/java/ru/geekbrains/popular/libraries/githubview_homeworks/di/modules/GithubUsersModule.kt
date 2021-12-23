package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.UsersScope
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers.UsersScopeContainer
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.GithubUsersRepositoryImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCache
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.cache.GithubUsersCacheImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubUsersRetrofit
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.retrofit.GithubUsersRetrofitImpl
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus

@Module
abstract class GithubUsersModule {

    companion object {
        @UsersScope
        @Provides
        fun usersRepo(
            networkStatus: NetworkStatus,
            githubUsersRetrofit: GithubUsersRetrofit,
            githubUsersCache: GithubUsersCache
        ): GithubUsersRepository {
            return GithubUsersRepositoryImpl(networkStatus, githubUsersRetrofit, githubUsersCache)
        }

        @UsersScope
        @Provides
        fun usersRetrofit(
            retrofitService: RetrofitService,
            db: AppDatabase
        ): GithubUsersRetrofit {
            return GithubUsersRetrofitImpl(retrofitService, db)
        }

        @UsersScope
        @Provides
        fun usersCache(
            db: AppDatabase
        ): GithubUsersCache {
            return GithubUsersCacheImpl(db)
        }

        @UsersScope
        @Provides
        fun scopeContainer(app: App): UsersScopeContainer = app
    }
}
