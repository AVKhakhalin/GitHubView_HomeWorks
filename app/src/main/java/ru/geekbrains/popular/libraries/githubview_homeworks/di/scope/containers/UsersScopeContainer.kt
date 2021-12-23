package ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers

import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.GithubUsersSubcomponent

interface UsersScopeContainer {

    fun initGithubUsersSubcomponent(): GithubUsersSubcomponent

    fun destroyGithubUsersSubcomponent()
}