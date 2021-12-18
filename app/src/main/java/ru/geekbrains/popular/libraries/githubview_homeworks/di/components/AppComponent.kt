package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.BindingModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.CiceroneModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.ContextModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.NetworkModule
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks.ForksPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainActivity
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainPresenter
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
//        BindingModule::class,
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class
    ]
)

interface AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)
    fun getMainActivity(): MainActivity

    fun routerInstance(): Router
    fun getNavigationHolder(): NavigatorHolder

    fun mainPresenter(): MainPresenter
    fun forksPresenter(): ForksPresenter

}