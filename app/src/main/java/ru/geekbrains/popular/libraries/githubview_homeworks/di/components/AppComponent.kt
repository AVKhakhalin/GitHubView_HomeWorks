package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import com.github.terrakok.cicerone.Router
import dagger.Component
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.BindingModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.CiceroneModule
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.ContextModule
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks.ForksPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainPresenter
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        BindingModule::class,
        ContextModule::class,
        CiceroneModule::class
    ]
)

interface AppComponent {

    fun routerInstance(): Router

    fun mainPresenter(): MainPresenter
    fun forksPresenter(): ForksPresenter
}