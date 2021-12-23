package ru.geekbrains.popular.libraries.githubview_homeworks.di.components

import dagger.Component
import ru.geekbrains.popular.libraries.githubview_homeworks.di.modules.*
import ru.geekbrains.popular.libraries.githubview_homeworks.domain.UserChooseRepository
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainActivity
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.main.MainPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos.ReposPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        AppModule::class,
        DbModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent {

    /** AppComponent */ //region
    fun mainPresenter(): MainPresenter
    fun injectMainActivity(mainActivity: MainActivity)
    fun userChoose(): UserChooseRepository
    //endregion

    /** Subcomponents */ //region
    fun reposPresenter(): ReposPresenter
    fun forksSubcomponent(): GithubForksSubcomponent
    fun usersSubcomponent(): GithubUsersSubcomponent
    //endregion
}