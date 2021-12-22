package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import dagger.Component
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.*
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks.ForksPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainActivity
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos.ReposPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class,
        CacheModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent {

    fun mainPresenter(): MainPresenter
    fun forksPresenter(): ForksPresenter
    fun usersPresenter(): UsersPresenter
    fun reposPresenter(): ReposPresenter

    fun injectMainActivity(mainActivity: MainActivity)

    fun userChoose(): UserChooseRepository
}