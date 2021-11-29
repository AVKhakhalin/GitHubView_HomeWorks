package ru.geekbrains.popular.libraries.githubview_homeworks.ui.main

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.screens.AppScreens

class MainPresenter(
    private val router: Router,
): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(AppScreens.usersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}