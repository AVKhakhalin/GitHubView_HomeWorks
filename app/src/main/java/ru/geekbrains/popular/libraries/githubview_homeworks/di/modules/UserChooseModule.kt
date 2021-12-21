package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepositoryImpl
import javax.inject.Singleton

@Module
class UserChooseModule(private val userChooseImpl: UserChooseRepositoryImpl) {

    @Provides
    @Singleton
    fun userChoose(): UserChooseRepository {
        return userChooseImpl
    }
}