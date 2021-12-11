package ru.geekbrains.popular.libraries.githubview_homeworks.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubRepoModel
import ru.geekbrains.popular.libraries.githubview_homeworks.model.GithubUserModel
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks.ForksFragment
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos.ReposFragment
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos.ReposPresenter
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UsersFragment

object AppScreens {
    /** Вызов фрагмента со списком логинов пользователей */
    fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }

    /** Вызов фрагмента с репозиторием пользователя */
    fun repoScreen(userModel: GithubUserModel) = FragmentScreen {
        ReposFragment.newInstance(userModel)
    }

    /** Вызов фрагмента с репозиторием пользователя */
    fun forksScreen(userModel: GithubUserModel, repoModel: GithubRepoModel,
                    presenter: ReposPresenter) = FragmentScreen {
        ForksFragment.newInstance(userModel, repoModel, presenter)
    }
}