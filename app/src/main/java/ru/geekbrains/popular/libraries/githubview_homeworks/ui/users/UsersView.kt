package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel

interface UsersView: MvpView {

    @AddToEndSingle
    fun updateList(users: List<GithubUserModel>)

//    @AddToEndSingle
//    fun showLoading()
//
//    @AddToEndSingle
//    fun hideLoading()
}