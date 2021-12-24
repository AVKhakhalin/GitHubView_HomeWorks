package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import dagger.Subcomponent
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.GithubForksModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.ForksScope
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks.ForksPresenter

@ForksScope
@Subcomponent(
    modules = [
        GithubForksModule::class
    ]
)

interface GithubForksSubcomponent {

    fun provideForksPresenter(): ForksPresenter
}