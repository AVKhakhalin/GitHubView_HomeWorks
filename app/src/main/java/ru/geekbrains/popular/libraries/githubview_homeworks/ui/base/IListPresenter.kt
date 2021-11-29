package ru.geekbrains.popular.libraries.githubview_homeworks.ui.base

interface IListPresenter<V: IItemView> {

    var itemClickListener: () -> Unit

    fun getCount(): Int

    fun bindView(view: V)
}