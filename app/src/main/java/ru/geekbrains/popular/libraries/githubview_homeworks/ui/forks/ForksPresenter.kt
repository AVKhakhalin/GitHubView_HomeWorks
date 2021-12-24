package ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.di.scope.containers.ForksScopeContainer
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainView
import javax.inject.Inject

class ForksPresenter @Inject constructor(
    private val router: Router,
    private val forksScopeContainer: ForksScopeContainer
) : MvpPresenter<MainView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    /** Уничтожение ForksSubcomponent при уничтожении данного презентера */
    override fun onDestroy() {
        forksScopeContainer.destroyForksSubcomponent()
        super.onDestroy()
    }
}