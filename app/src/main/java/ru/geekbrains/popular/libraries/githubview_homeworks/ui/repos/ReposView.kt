package ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel

@AddToEndSingle
interface ReposView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showRepos(repos: List<GithubRepoModel>)
}