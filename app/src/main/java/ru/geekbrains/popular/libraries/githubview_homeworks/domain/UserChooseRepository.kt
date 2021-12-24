package ru.geekbrains.popular.libraries.githubview_homeworks.domain

import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

interface UserChooseRepository {
    fun setGithubUserModel(githubUserModel: GithubUserModel)
    fun setUsersModel(users: List<GithubUserModel>)
    fun getGithubUserModel(): GithubUserModel
    fun getUsersModel(): List<GithubUserModel>
    fun setGithubRepoModel(githubRepoModel: GithubRepoModel)
    fun setReposModel(repos: List<GithubRepoModel>)
    fun getGithubRepoModel(): GithubRepoModel
}