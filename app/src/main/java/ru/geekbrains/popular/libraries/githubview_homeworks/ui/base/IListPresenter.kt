package ru.geekbrains.popular.libraries.githubview_homeworks.ui.base

import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: (UserItemView) -> Unit

    fun getCount(): Int

    fun bindView(view: V)
}