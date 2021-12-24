package ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers

import ru.geekbrains.popular.libraries.githubview_homeworks.di.components.GithubReposSubcomponent

interface ReposScopeContainer {

    fun initGithubReposSubcomponent(): GithubReposSubcomponent

    fun destroyGithubReposSubcomponent()
}