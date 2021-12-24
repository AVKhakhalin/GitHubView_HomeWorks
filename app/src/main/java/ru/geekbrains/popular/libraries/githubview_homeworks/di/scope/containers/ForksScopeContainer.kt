package ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers

import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.GithubForksSubcomponent

interface ForksScopeContainer {

    fun initForksSubcomponent(): GithubForksSubcomponent

    fun destroyForksSubcomponent()
}