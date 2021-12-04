package ru.geekbrains.popular.libraries.githubview_homeworks.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersView: MvpView {

    @AddToEndSingle
    fun updateList()
}