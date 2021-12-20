package ru.geekbrains.popular.libraries.githubview_homeworks.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.forks.ForksFragment
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.repos.ReposFragment
import ru.geekbrains.popular.libraries.githubview_homeworks.ui.users.UsersFragment

interface AppScreens {
    fun usersScreen(): FragmentScreen
    fun repoScreen(): FragmentScreen
    fun forksScreen(): FragmentScreen
}

class AppScreensImpl : AppScreens {
    /** Вызов фрагмента со списком логинов пользователей */
    override fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }

    /** Вызов фрагмента с репозиторием пользователя */
    override fun repoScreen() = FragmentScreen {
        ReposFragment.newInstance()
    }

    /** Вызов фрагмента с репозиторием пользователя */
    override fun forksScreen() = FragmentScreen {
        ForksFragment.newInstance()
    }
}