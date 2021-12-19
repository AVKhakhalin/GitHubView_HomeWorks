package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppScreen
import dagger.Component
import ru.geekbrains.popular.libraries.githubview_homeworks.db.AppDatabase
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.CacheModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.CiceroneModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.ContextModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.NetworkModule
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.RetrofitService
import ru.geekbrains.popular.libraries.githubview_homeworks.remote.connectivity.NetworkStatus
import ru.geekbrains.popular.libraries.githubview_homeworks.screens.AppScreens
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks.ForksPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainActivity
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainPresenter
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class,
        CacheModule::class
    ]
)

interface AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)

    fun routerInstance(): Router
    fun getNavigationHolder(): NavigatorHolder

    fun mainPresenter(): MainPresenter
    fun forksPresenter(): ForksPresenter

    fun networkStatus(): NetworkStatus

    fun db(): AppDatabase
    fun retrofit(): RetrofitService

    fun appScreens(): AppScreens

}