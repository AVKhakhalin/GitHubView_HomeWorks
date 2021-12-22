package ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainView
import javax.inject.Inject

class ForksPresenter @Inject constructor(
    private val router: Router
): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}