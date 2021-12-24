package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import dagger.Subcomponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.GithubUsersModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.UsersScope
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UsersPresenter

@UsersScope
@Subcomponent(
    modules = [
        GithubUsersModule::class
    ]
)
interface GithubUsersSubcomponent {

    fun provideUsersPresenter(): UsersPresenter
}