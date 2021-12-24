package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import dagger.Subcomponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.GithubReposModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.ReposScope
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos.ReposPresenter


@ReposScope
@Subcomponent(
    modules = [
        GithubReposModule::class
    ]
)

interface GithubReposSubcomponent {

    fun provideReposPresenter(): ReposPresenter
}