package ru.geekbrains.popular.libraries.githubview_homeworks.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.popular.libraries.githubview_homeworks.App
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.ForksScope
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers.ForksScopeContainer

@Module
abstract class GithubForksModule {

    companion object {
        @ForksScope
        @Provides
        fun forksScopeContainer(app: App): ForksScopeContainer = app
    }
}
